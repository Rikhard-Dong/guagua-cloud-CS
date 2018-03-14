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


    /**
     * 用户登录功能
     *
     * @param account  账号
     * @param password 密码
     * @param loginIp  登录ip
     * @return 用户登录, 返回token信息
     */
    ResultDto login(String account, String password, String loginIp);

    /**
     * 重置密码 在用户忘记密码的情况下无法登录, 通过手机发送验证码重置密码
     *
     * @param phone      手机号
     * @param code       验证码
     * @param password   新密码
     * @param repassword 确认密码
     * @return 操作结果
     */
    ResultDto resetPassword(String phone, String code, String password, String repassword);
}
