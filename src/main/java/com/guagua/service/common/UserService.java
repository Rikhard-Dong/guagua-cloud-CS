package com.guagua.service.common;


import com.guagua.bean.dto.ResultDTO;
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
    ResultDTO isPhoneExists(String phone);

    /**
     * 发送验证码,
     *
     * @param phone 待发送手机号
     * @return 返回结果
     * @throws CustomException 异常
     */
    ResultDTO sendSMSCode(String phone) throws CustomException;

    /**
     * 用户注册
     *
     * @param user 待注册用户
     * @param code 验证码
     * @return 结果
     * @throws CustomException 异常
     */
    ResultDTO register(User user, String code) throws CustomException;


    /**
     * 用户登录功能
     *
     * @param account  账号
     * @param password 密码
     * @param loginIp  登录ip
     * @return 用户登录, 返回token信息
     */
    ResultDTO login(String account, String password, String loginIp);

    /**
     * 重置密码 在用户忘记密码的情况下无法登录, 通过手机发送验证码重置密码
     *
     * @param phone      手机号
     * @param code       验证码
     * @param password   新密码
     * @param repassword 确认密码
     * @return 操作结果
     */
    ResultDTO resetPassword(String phone, String code, String password, String repassword);

    /**
     * 获取用户的基本信息, 比如用户头像和用户昵称
     *
     * @param userId 查询用户id
     * @return 用户基本信息
     */
    ResultDTO getSimpleUserInfo(Integer userId);

    /**
     * 获取用户的详细信息
     *
     * @param userId 用户id
     * @return result
     */
    ResultDTO getUserInfo(Integer userId);

    /**
     * 更新密码
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param password    新密码
     * @param repassword  确认密码
     * @return 结果
     */
    ResultDTO updatePassword(Integer userId, String oldPassword, String password, String repassword);

    /**
     * 更新用户头像
     *
     * @param userId    用户id
     * @param imgBase64 图片base64编码
     * @return 更新结果
     */
    ResultDTO updateHeadImage(Integer userId, String imgBase64);

    /**
     * 更新用户名
     *
     * @param userId
     * @param username
     * @return
     */
    ResultDTO updateUsername(Integer userId, String username);

    /**
     * 更新用户性别
     *
     * @param userId
     * @param sex
     * @return
     */
    ResultDTO updateSex(Integer userId, Integer sex);

    /**
     * 更新用户qq
     *
     * @param userId
     * @param qq
     * @return
     */
    ResultDTO updateQQ(Integer userId, String qq);

    /**
     * 更新用户微信
     *
     * @param userId
     * @param wechat
     * @return
     */
    ResultDTO updateWechat(Integer userId, String wechat);

    /**
     * 更新用户描述
     *
     * @param userId
     * @param description
     * @return
     */
    ResultDTO updateDescription(Integer userId, String description);

    /**
     * 更新用户教育经历
     *
     * @param userId
     * @param educational
     * @return
     */
    ResultDTO updateEducational(Integer userId, String educational);

    /**
     * 更新用户地址
     *
     * @param userId
     * @param address
     * @return
     */
    ResultDTO updateAddress(Integer userId, String address);
}
