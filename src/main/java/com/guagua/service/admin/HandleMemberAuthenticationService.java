package com.guagua.service.admin;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-17 下午10:23
 * <p>
 * 处理用户实名认证结果
 */
public interface HandleMemberAuthenticationService {

    /**
     * 审核用户实名信息认证是否成功
     *
     * @param userId  用户id, 用于判断当前用户是否为后台用户以及拥有相关角色
     * @param applyId 对应用户申请的result的申请结果
     * @param result  处理结果, true 实名认证成功 false 失败
     * @param comment 批注
     * @return result
     */
    ResultDTO handleAuthentication(Integer userId, Integer applyId, boolean result, String comment);
}
