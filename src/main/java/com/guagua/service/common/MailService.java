package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-15 下午9:02
 * <p>
 * 关于邮件的服务
 */
public interface MailService {

    /**
     * 发送一封激活邮件
     *
     * @param userId 用户id
     * @param email  用户邮箱
     * @return 结果
     */
    ResultDTO sendEmailCode(Integer userId, String email);

    /**
     * 绑定/重新绑定email
     *
     * @param userId 用户id
     * @param email  用户邮箱
     * @param code   验证码
     * @return result
     */
    ResultDTO updateMail(Integer userId, String email, String code);
}
