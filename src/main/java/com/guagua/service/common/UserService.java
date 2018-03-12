package com.guagua.service.common;


import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.entity.common.User;
import com.guagua.exception.common.CustomException;

/**
 * @author ride
 * @date 18-3-12 下午2:35
 * <p>
 * user service 接口
 */
public interface UserService {

    /**
     * 判断手机号是否已经注册
     *
     * @param phone phone
     * @return 返回结果
     */
    ResultDto isPhoneExists(String phone);

    /**
     * 发送验证码,
     *
     * @param phone 待发送手机号
     * @return 返回结果
     * @throws CustomException 异常
     */
    ResultDto sendSMSCode(String phone) throws CustomException;

    /**
     * 用户注册
     *
     * @param user 待注册用户
     * @param code 验证码
     * @return 结果
     * @throws CustomException 异常
     */
    ResultDto register(User user, String code) throws CustomException;
}
