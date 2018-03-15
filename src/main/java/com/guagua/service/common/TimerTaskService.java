package com.guagua.service.common;

/**
 * @author ride
 * @date 18-3-12 下午6:22
 * <p>
 * 定时任务服务
 */
public interface TimerTaskService {

    /**
     * 清空t_phone_validate_code表中的无效数据
     */
    void clearTablePhoneValidateCodeData();

    /**
     * 清空他t_mail_validate_code表中的无效数据
     */
    void clearTableMailValidateCodeData();

}
