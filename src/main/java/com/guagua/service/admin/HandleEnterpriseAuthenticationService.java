package com.guagua.service.admin;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-17 下午10:25
 * <p>
 * 处理企业用户的实名认证信息
 */
public interface HandleEnterpriseAuthenticationService {
    /**
     * 处理企业用户认证信息
     *
     * @param userId  处理人员id
     * @param applyId 认证请求id
     * @param result  审核结果
     * @param comment 批注
     * @return result
     */
    ResultDTO handleAuthentication(Integer userId, Integer applyId, boolean result, String comment);
}
