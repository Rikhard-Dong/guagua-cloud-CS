package com.guagua.service.common.impl;

import com.guagua.bean.entity.common.PhoneValidateCode;
import com.guagua.dao.common.PhoneValidateCodeDao;
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

    private final PhoneValidateCodeDao codeDao;

    @Autowired
    public TimerTaskServiceImpl(PhoneValidateCodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Transactional
    public void clearTablePhoneValidateCodeData() {
        logger.info("#################clear table t_phone_validate_code data####################");

        List<PhoneValidateCode> codes = codeDao.listAll();

        for (PhoneValidateCode code : codes) {
            if (code.isExpire()) {
                Integer flag = codeDao.deleteById(code.getId());
                if (flag == 0) {
                    logger.warn("删除过期PhoneValidateCode失败 ===> {}", code);
                }
            }
        }
    }
}
