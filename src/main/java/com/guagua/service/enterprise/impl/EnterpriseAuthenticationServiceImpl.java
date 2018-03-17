package com.guagua.service.enterprise.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.dto.enterprise.EnterpriseAuthenticationDTO;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.enterprise.EnterpriseAuthentication;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.enterprise.EnterpriseAuthenticationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.enterprise.EnterpriseAuthenticationService;

import com.guagua.utils.QiniuUtils;
import com.guagua.utils.UUIDUtils;
import com.qiniu.common.QiniuException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ride
 * @date 18-3-17 下午2:52
 * <p>
 * 处理企业用户提交的信息认证
 */
@Service("enterpriseAuthenticationService")
public class EnterpriseAuthenticationServiceImpl
        extends BaseService implements EnterpriseAuthenticationService {

    // 七牛云上传前缀域名
    private static String QINIU_DOMAIN = "http://p5etjjbs6.bkt.clouddn.com/";


    private final UserDao userDao;

    private final EnterpriseAuthenticationDao authenticationDao;

    @Autowired
    public EnterpriseAuthenticationServiceImpl(UserDao userDao,
                                               EnterpriseAuthenticationDao authenticationDao) {
        this.userDao = userDao;
        this.authenticationDao = authenticationDao;
    }

    // 查询认证信息
    public ResultDto getInfo(Integer userId) {
        // 判断用户是否存在且用户类型为1, 企业类型
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 1) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);

        }

        EnterpriseAuthentication authentication = authenticationDao.findByUserId(userId);

        EnterpriseAuthenticationDTO dto =
                authentication == null ? null : new EnterpriseAuthenticationDTO(authentication);
        if (dto != null) {
            dto.setUsername(user.getUsername());
        }

        return new ResultDto(DataDictionary.QUERY_AUTHENTICATION_SUCCESS).addData("info", dto);
    }

    // 申请认证
    public ResultDto apply(Integer userId, String realName, String idNumber, String enterpriseName,
                           String businessLicense, String licensedResidence, String postalAddress,
                           String legalPerson, String officePhone, String enterpriseDescription,
                           String businessLicenseImgBase64, String positiveImgBase64,
                           String negativeImgBase64) {
        // 判断用户是否存在且用户类型为1, 企业类型
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 1) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);
        }

        EnterpriseAuthentication authentication = authenticationDao.findByUserId(user.getId());
        if (authentication != null) {
            // 已经有认证信息, 不能重新认证
            return new ResultDto(DataDictionary.HAS_AUTHENTICATION);
        }

        // 上传的文件信息
        String businessLicenseFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";
        String positiveFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";
        String negativeFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";

        try {
            QiniuUtils.uploadByBase64(businessLicenseImgBase64, businessLicenseFilename);
            QiniuUtils.uploadByBase64(positiveImgBase64, positiveFilename);
            QiniuUtils.uploadByBase64(negativeImgBase64, negativeFilename);
        } catch (Exception e) {
            logger.error("图片上传失败 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        // 外链地址
        String businessLicenseUrl = QINIU_DOMAIN + businessLicenseFilename;
        String positiveUrl = QINIU_DOMAIN + positiveFilename;
        String negativeUrl = QINIU_DOMAIN + negativeFilename;

        // 创建认证对象
        EnterpriseAuthentication var1 = new EnterpriseAuthentication();
        var1.setUserId(user.getId());
        var1.setRealName(realName);
        var1.setIdNumber(idNumber);
        var1.setEnterpriseName(enterpriseName);
        var1.setBusinessLicense(businessLicense);
        var1.setLicensedResidence(licensedResidence);
        var1.setPostalAddress(postalAddress);
        var1.setLegalPerson(legalPerson);
        var1.setOfficePhone(officePhone);
        var1.setEnterpriseDescription(enterpriseDescription);
        var1.setBusinessLicenseImg(businessLicenseUrl);
        var1.setPositiveImg(positiveUrl);
        var1.setNegativeImg(negativeUrl);

        Integer var2 = authenticationDao.insertOne(var1);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.AUTHENTICATION_APPLY_SUCCESS);
    }

    // 申请重新认证
    public ResultDto reapply(Integer userId, String realName, String idNumber, String enterpriseName,
                             String businessLicense, String licensedResidence, String postalAddress,
                             String legalPerson, String officePhone, String enterpriseDescription,
                             String businessLicenseImgBase64, String positiveImgBase64,
                             String negativeImgBase64) {
        // 判断用户是否存在且用户类型为1, 企业类型
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        if (user.getType() != 1) {
            return new ResultDto(DataDictionary.USER_TYPE_ERROR);
        }

        // 应当查询出一条数据来, 且状态应该为2, 审核不通过的状态
        EnterpriseAuthentication authentication = authenticationDao.findByUserId(user.getId());
        if (authentication == null) {
            return new ResultDto(DataDictionary.QUERY_AUTHENTICATION_FAIL);
        }
        if (authentication.getStatus() != 2) {
            return new ResultDto(DataDictionary.AUTHENTICATION_STATUS_ERROR);
        }

        // 上传的文件信息
        String businessLicenseFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";
        String positiveFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";
        String negativeFilename = "img/authentication/enterprise/" + UUIDUtils.getUUID() + ".jpg";

        // 获取之前上传的内容
        String businessLicenseUrlOld = authentication.getBusinessLicenseImg();
        String positiveUrlOld = authentication.getPositiveImg();
        String negativeUrlOld = authentication.getNegativeImg();

        // 上传图片
        try {
            QiniuUtils.uploadByBase64(businessLicenseImgBase64, businessLicenseFilename);
            QiniuUtils.uploadByBase64(positiveImgBase64, positiveFilename);
            QiniuUtils.uploadByBase64(negativeImgBase64, negativeFilename);
        } catch (Exception e) {
            logger.error("图片上传失败 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        // 拼接外链地址
        String businessLicenseUrl = QINIU_DOMAIN + businessLicenseFilename;
        String positiveUrl = QINIU_DOMAIN + positiveFilename;
        String negativeUrl = QINIU_DOMAIN + negativeFilename;

        // 创建认证对象
        EnterpriseAuthentication var1 = new EnterpriseAuthentication();
        var1.setUserId(user.getId());
        var1.setRealName(realName);
        var1.setIdNumber(idNumber);
        var1.setEnterpriseName(enterpriseName);
        var1.setBusinessLicense(businessLicense);
        var1.setLicensedResidence(licensedResidence);
        var1.setPostalAddress(postalAddress);
        var1.setLegalPerson(legalPerson);
        var1.setOfficePhone(officePhone);
        var1.setEnterpriseDescription(enterpriseDescription);
        var1.setBusinessLicenseImg(businessLicenseUrl);
        var1.setPositiveImg(positiveUrl);
        var1.setNegativeImg(negativeUrl);
        var1.setStatus(0);  // 状态设为未审核

        Integer var2 = authenticationDao.insertOne(var1);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 删除原有对象
        businessLicenseUrlOld = StringUtils.removeAll(businessLicenseUrlOld, QINIU_DOMAIN);
        positiveUrlOld = StringUtils.removeAll(positiveUrlOld, QINIU_DOMAIN);
        negativeUrlOld = StringUtils.removeAll(negativeUrlOld, QINIU_DOMAIN);

        try {
            QiniuUtils.deleteImg(businessLicenseUrlOld);
            QiniuUtils.deleteImg(positiveUrlOld);
            QiniuUtils.deleteImg(negativeUrlOld);
        } catch (QiniuException e) {
            logger.error("删除图片异常 ===> {}", e);
            throw new CustomException(DataDictionary.QINIU_OPERATION_FAIL);
        }

        return new ResultDto(DataDictionary.AUTHENTICATION_APPLY_SUCCESS);
    }

    // 获取所有
    public ResultDto getAllAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseAuthentication> var = authenticationDao.findAll();

        if (var != null) {
            List<EnterpriseAuthenticationDTO> var2 = getDtos(var);
            PageInfo<EnterpriseAuthenticationDTO> info = new PageInfo<EnterpriseAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);

        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 获取未审核
    public ResultDto getAllUnauditedAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseAuthentication> var = authenticationDao.findAllUnaudited();

        if (var != null) {
            List<EnterpriseAuthenticationDTO> var2 = getDtos(var);
            PageInfo<EnterpriseAuthenticationDTO> info = new PageInfo<EnterpriseAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);

        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 获取审核通过
    public ResultDto getAllPassAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseAuthentication> var = authenticationDao.findAllPass();

        if (var != null) {
            List<EnterpriseAuthenticationDTO> var2 = getDtos(var);
            PageInfo<EnterpriseAuthenticationDTO> info = new PageInfo<EnterpriseAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);

        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    // 获取所有审核失败
    public ResultDto getAllFailPassAuthentication(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseAuthentication> var = authenticationDao.findAllFailPass();

        if (var != null) {
            List<EnterpriseAuthenticationDTO> var2 = getDtos(var);
            PageInfo<EnterpriseAuthenticationDTO> info = new PageInfo<EnterpriseAuthenticationDTO>(var2);
            return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", info);

        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("pageInfo", null);
    }

    private List<EnterpriseAuthenticationDTO> getDtos(List<EnterpriseAuthentication> lists) {
        List<EnterpriseAuthenticationDTO> authentications = new ArrayList<EnterpriseAuthenticationDTO>();

        for (EnterpriseAuthentication var : lists) {
            EnterpriseAuthenticationDTO var1 = new EnterpriseAuthenticationDTO(var);
            User var2 = userDao.findById(var.getUserId());
            if (var2 != null) {
                var1.setUsername(var2.getUsername());
            }
            authentications.add(var1);
        }
        return authentications;
    }
}
