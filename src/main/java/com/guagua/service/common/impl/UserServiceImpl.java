package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.entity.common.PhoneValidateCode;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.PhoneValidateCodeDao;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.UserService;
import com.guagua.utils.RegExpUtils;
import com.guagua.utils.SMSCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-12 下午2:37
 */

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    private final UserDao userDao;

    private final PhoneValidateCodeDao codeDao;

    @Autowired
    public UserServiceImpl(PhoneValidateCodeDao codeDao, UserDao userDao) {
        this.codeDao = codeDao;
        this.userDao = userDao;
    }


    // 判断手机哈偶是否存在
    public ResultDto isPhoneExists(String phone) {
        User user = userDao.findByPhone(phone);
        return user == null ? new ResultDto(DataDictionary.LEGAL_PHONE) :
                new ResultDto(DataDictionary.ALREADY_REGISTER_PHONE);
    }

    // 发送验证码短信
    @Transactional
    public ResultDto sendSMSCode(String phone) throws CustomException {
        boolean flag = !RegExpUtils.isPhoneLegal(phone);
        if (flag) {
            // 输入的手机号不合法
            throw new CustomException(DataDictionary.ILLEGAL_PHONE);
        }

        PhoneValidateCode code = codeDao.findLatestPhoneValidateCodeByPhone(phone);
        if (code != null) {
            Date currTime = new Date();
            Date expireTime = new Date(code.getSendTime().getTime() + 60 * 1000);
            if (currTime.before(expireTime)) {
                // 请求过于频繁, 两次验证码发送间隔为1分钟
                throw new CustomException(DataDictionary.VERIFYING_CODE_REQUEST_FREQUENT);
            } else {
                codeDao.deleteById(code.getId());
            }

        }

        String codeStr = SMSCodeUtils.SendAlicomMsgDemo(phone);
        Integer flag2 = codeDao.insertPhoneValidateCode(new PhoneValidateCode(phone, codeStr));

        if (flag2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.ALREADY_SEND_CODE);
    }

    // 用户注册
    public ResultDto register(User user, String code) throws CustomException {
        boolean flag = !RegExpUtils.isPhoneLegal(user.getPhone());
        if (flag) {
            // 输入的手机号不合法
            throw new CustomException(DataDictionary.ILLEGAL_PHONE);
        }
        User var1 = userDao.findByPhone(user.getPhone());
        if (var1 != null) {
            // 该手机号已经注册了
            throw new CustomException(DataDictionary.ALREADY_REGISTER_PHONE);
        }
        if (user.getType() != 1 && user.getType() != 2) {
            // 注册用户类型不正确 1. 企业用户  2. 会员用户
            throw new CustomException(DataDictionary.USER_REGISTER_TYPE_ERROR);
        }

        PhoneValidateCode var2 = codeDao.findLatestPhoneValidateCodeByPhone(user.getPhone());
        String var3 = var2 == null ? "" : var2.getCode();
        if (!StringUtils.equals(code, var3)) {
            // 验证码不匹配
            throw new CustomException(DataDictionary.USER_REGISTER_VALIDATE_CODE_NOT_MATCH);
        }

        // 设置默认用户名
        Integer var4 = userDao.findMaxId();
        String username = "用户" + ((var4 == null ? 0 : var4) + 1);
        user.setUsername(username);

        Integer var5 = userDao.insertUser(user);
        if (var5 == 0) {
            // 用户插入失败
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        
        // 验证结束后从数据库中删除
        if (var2 != null) {
            codeDao.deleteById(var2.getId());
        }

        return new ResultDto(DataDictionary.REGISTER_SUCCESS);
    }
}
