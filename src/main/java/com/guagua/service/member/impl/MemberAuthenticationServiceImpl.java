package com.guagua.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.dto.member.MemberAuthenticationDTO;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.member.MemberAuthentication;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.member.MemberAuthenticationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.member.MemberAuthenticationService;
import com.guagua.utils.QiniuUtils;
import com.guagua.utils.UUIDUtils;
import com.qiniu.common.QiniuException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ride
 * @date 18-3-16 下午6:38
 * <p>
 * 实现类
 */
@Service("memberAuthenticationService")
public class MemberAuthenticationServiceImpl
        extends BaseService implements MemberAuthenticationService {


    private final UserDao userDao;

    private final MemberAuthenticationDao authenticationDao;

    @Autowired
    public MemberAuthenticationServiceImpl(UserDao userDao, MemberAuthenticationDao authenticationDao) {
        this.userDao = userDao;
        this.authenticationDao = authenticationDao;
    }

    // 查询用户认证信息
    public ResultDto getInfo(Integer userId) {
        // 首先判断用户是否存在且用户类型是2
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 2) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);
        }

        MemberAuthentication authentication = authenticationDao.findByUserId(user.getId());

        MemberAuthenticationDTO dto =
                authentication == null ? null : new MemberAuthenticationDTO(authentication);

        if (dto != null) {
            dto.setUsername(user.getUsername());
        }

        return new ResultDto(DataDictionary.QUERY_AUTHENTICATION_SUCCESS).addData("info", dto);
    }

    // 申请
    @Transactional
    public ResultDto apply(Integer userId, String realName, String idNumber,
                           String positiveBase64, String negativeBase64) {
        // 首先判断用户是否存在且用户类型是2
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 2) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);
        }

        MemberAuthentication authentication = authenticationDao.findByUserId(user.getId());
        if (authentication != null) {
            // 已经有认证信息, 认证失败
            return new ResultDto(DataDictionary.HAS_AUTHENTICATION);
        }

        String positiveFilename = "img/authentication/member/" + UUIDUtils.getUUID() + ".jpg";
        String negativeFilename = "img/authentication/member/" + UUIDUtils.getUUID() + ".jpg";

        try {
            QiniuUtils.uploadByBase64(positiveBase64, positiveFilename);
            QiniuUtils.uploadByBase64(negativeBase64, negativeFilename);
        } catch (Exception e) {
            logger.error("图片上传失败 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        String positiveUrl = "http://p5etjjbs6.bkt.clouddn.com/" + positiveFilename;
        String negativeUrl = "http://p5etjjbs6.bkt.clouddn.com/" + negativeFilename;

        MemberAuthentication var1 =
                new MemberAuthentication(user.getId(), realName, idNumber, positiveUrl, negativeUrl);

        Integer var2 = authenticationDao.insertOne(var1);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDto(DataDictionary.AUTHENTICATION_APPLY_SUCCESS);
    }

    // 重新申请
    @Transactional
    public ResultDto reapply(Integer userId, String realName, String idNumber,
                             String positiveBase64, String negativeBase64) {
        // 首先判断用户是否存在且用户类型是2
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 2) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);
        }
        // 应该要查询出一条结果
        MemberAuthentication authentication = authenticationDao.findByUserId(user.getId());
        if (authentication == null) {
            return new ResultDto(DataDictionary.QUERY_AUTHENTICATION_FAIL);
        }

        // 认证状态应该为2, 审核失败才能重新提交审核
        if (authentication.getStatus() != 2) {
            return new ResultDto(DataDictionary.AUTHENTICATION_STATUS_ERROR);
        }

        String positiveFilename = "img/authentication/member/" + UUIDUtils.getUUID() + ".jpg";
        String negativeFilename = "img/authentication/member/" + UUIDUtils.getUUID() + ".jpg";

        // 获取之前上传的内容
        String positiveFilenameOld = authentication.getCertificatesPositive();
        String negativeFilenameOld = authentication.getCertificatesNegative();

        // 上传图片
        try {
            QiniuUtils.uploadByBase64(positiveBase64, positiveFilename);
            QiniuUtils.uploadByBase64(negativeBase64, negativeFilename);
        } catch (Exception e) {
            logger.error("图片上传失败 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        // 上传成功的完整外链
        String positiveUrl = "http://p5etjjbs6.bkt.clouddn.com/" + positiveFilename;
        String negativeUrl = "http://p5etjjbs6.bkt.clouddn.com/" + negativeFilename;

        // 重新提交申请
        authentication.setRealName(realName);
        authentication.setIdNumber(idNumber);
        authentication.setCertificatesPositive(positiveUrl);
        authentication.setCertificatesNegative(negativeUrl);
        authentication.setStatus(0);

        Integer var1 = authenticationDao.updateById(authentication);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        positiveFilenameOld = StringUtils.removeAll(positiveFilenameOld, "http://p5etjjbs6.bkt.clouddn.com/");
        negativeFilenameOld = StringUtils.removeAll(negativeFilenameOld, "http://p5etjjbs6.bkt.clouddn.com/");

        try {
            QiniuUtils.deleteImg(positiveFilenameOld);
            QiniuUtils.deleteImg(negativeFilenameOld);
        } catch (QiniuException e) {
            logger.error("删除图片异常 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        return new ResultDto(DataDictionary.AUTHENTICATION_APPLY_SUCCESS);
    }

    // 查询会员用户所有认证信息
    public ResultDto getAllAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberAuthentication> var = authenticationDao.findAll();
        if (var != null) {
            List<MemberAuthenticationDTO> var2 = getDtos(var);
            PageInfo<MemberAuthenticationDTO> info = new PageInfo<MemberAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);
        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 查询所有审核通过
    public ResultDto getAllPassAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberAuthentication> var = authenticationDao.findAllPass();
        if (var != null) {
            List<MemberAuthenticationDTO> var2 = getDtos(var);
            PageInfo<MemberAuthenticationDTO> info = new PageInfo<MemberAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);
        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 查询所有审核不通过的
    public ResultDto getAllUnauditedAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberAuthentication> var = authenticationDao.findAllUnaudited();
        if (var != null) {
            List<MemberAuthenticationDTO> var2 = getDtos(var);
            PageInfo<MemberAuthenticationDTO> info = new PageInfo<MemberAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);
        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 查询所有审核不通过的
    public ResultDto getAllFailPassAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberAuthentication> var = authenticationDao.findAllFailPass();
        if (var != null) {
            List<MemberAuthenticationDTO> var2 = getDtos(var);
            PageInfo<MemberAuthenticationDTO> info = new PageInfo<MemberAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);
        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    /**
     * 将查询到的认证信息转换为dto
     *
     * @param lists
     * @return
     */
    private List<MemberAuthenticationDTO> getDtos(List<MemberAuthentication> lists) {

        List<MemberAuthenticationDTO> authentications = new ArrayList<MemberAuthenticationDTO>();
        for (MemberAuthentication var : lists) {
            MemberAuthenticationDTO var1 = new MemberAuthenticationDTO(var);
            User var2 = userDao.findById(var.getUserId());
            var1.setUsername(var2.getUsername());
            authentications.add(var1);
        }

        return authentications;
    }
}
