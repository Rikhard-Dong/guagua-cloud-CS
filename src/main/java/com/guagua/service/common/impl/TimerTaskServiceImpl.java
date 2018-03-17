package com.guagua.service.common.impl;

import com.guagua.bean.entity.common.EmailValidateCode;
import com.guagua.bean.entity.common.PhoneValidateCode;
import com.guagua.dao.common.EmailValidateCodeDao;
import com.guagua.dao.common.PhoneValidateCodeDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ride
 * @date 18-3-12 下午6:24
 * <p>
 * 实现类
 */
@Service("timeTaskService")
public class TimerTaskServiceImpl extends BaseService implements TimerTaskService {

    private final PhoneValidateCodeDao phoneCodeDao;

    private final EmailValidateCodeDao mailCodeDao;

    @Autowired
    public TimerTaskServiceImpl(PhoneValidateCodeDao codeDao, EmailValidateCodeDao mailCodeDao) {
        this.phoneCodeDao = codeDao;
        this.mailCodeDao = mailCodeDao;
    }

    // 清除短信验证码
    @Transactional
    public void clearTablePhoneValidateCodeData() {
//        logger.info("#################clear table t_phone_validate_code data####################");

        List<PhoneValidateCode> codes = phoneCodeDao.findAll();

        for (PhoneValidateCode code : codes) {
            if (code.isExpire()) {
                Integer flag = phoneCodeDao.deleteById(code.getId());
                if (flag == 0) {
                    logger.warn("删除过期PhoneValidateCode失败 ===> {}", code);
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }
    }

    // 清除手机验证码
    @Transactional
    public void clearTableMailValidateCodeData() {
//        logger.info("#################clear table t_email_validate_code data####################");

        List<EmailValidateCode> codes = mailCodeDao.findAll();
        for (EmailValidateCode code : codes) {
            if (code.isExpire()) {
                Integer flag = mailCodeDao.deleteEmailValidateCodeById(code.getId());
                if (flag == 0) {
                    logger.warn("删除过期EMailValidateCode失败 ===> {}", code);
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }

    }
}
