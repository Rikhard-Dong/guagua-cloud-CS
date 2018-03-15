package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.entity.common.EmailValidateCode;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.EmailValidateCodeDao;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.MailService;

import com.guagua.utils.MailUtils;
import com.guagua.utils.RandomCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ride
 * @date 18-3-15 下午9:03
 * <p>
 * 邮箱相关操作
 */
@Service("mailService")
public class MailServiceImpl extends BaseService implements MailService {

    private final UserDao userDao;

    private final EmailValidateCodeDao emailDao;

    @Autowired
    public MailServiceImpl(UserDao userDao, EmailValidateCodeDao emailDao) {
        this.userDao = userDao;
        this.emailDao = emailDao;
    }

    // 发送一封含验证码的激活邮件
    public ResultDto sendEmailCode(Integer userId, String email) {
        User user = userDao.findById(userId);
        if (user == null) {
            // 用户不存在
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        User var1 = userDao.findByEmail(email);
        if (var1 != null) {
            // 邮箱已经存在, 不能继续绑定
            return new ResultDto(DataDictionary.EMAIL_IS_EXISTS);
        }

        // 生成随机验证码
        String code = RandomCodeUtils.randomCode();
        EmailValidateCode var2 = new EmailValidateCode(userId, email, code);
        Integer var3 = emailDao.insertEmailValidateCode(var2);
        // 插入数据失败
        if (var3 == 0) {
            return new ResultDto(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        String subject = "[丁丁云客服众包平台] 验证码";
        String content = "<h3>[丁丁云客服众包平台]</h3><br>" +
                "您的验证码是: <" + code + "><br>" +
                "十五分钟内有效";
        new Thread(new MailUtils(email, subject, content)).run();

        return new ResultDto(DataDictionary.SEND_EMAIL_CODE_SUCCESS);
    }

    @Transactional
    public ResultDto updateMail(Integer userId, String email, String code) {
        User user = userDao.findById(userId);
        if (user == null) {
            // 用户不存在
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        User var1 = userDao.findByEmail(email);
        if (var1 != null) {
            // 邮箱已经存在, 不能继续绑定
            return new ResultDto(DataDictionary.EMAIL_IS_EXISTS);
        }

        EmailValidateCode var2 = emailDao.findLatestEmailValidateCodeByEmail(email);

        if (var2 == null) {
            // 验证码已经过期或者不存在
            return new ResultDto(DataDictionary.VALIDATE_CODE_EXPIRE);
        }
        if (!var2.getUserId().equals(user.getId())) {
            // 发送验证码的用户和提交验证码的用户不是同一个
            return new ResultDto(DataDictionary.SEND_EMAIL_USER_NOT_MATCH);
        }
        if (!StringUtils.equals(code, var2.getCode())) {
            // 验证码不一致
            return new ResultDto(DataDictionary.VALIDATE_CODE_NOT_MATCH);
        }

        Integer var3 = userDao.updateEmail(userId, email);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.BIND_EMAIL_SUCCESS);
    }
}
