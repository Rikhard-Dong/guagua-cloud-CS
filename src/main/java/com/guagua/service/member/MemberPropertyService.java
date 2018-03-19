package com.guagua.service.member;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-19 下午7:46
 */
public interface MemberPropertyService {

    /**
     * 查询资金明细
     *
     * @param userId
     * @return
     */
    ResultDTO queryProperty(Integer userId);

    /**
     * 查询资金流水
     *
     * @param userId 用户id
     * @param page   页数
     * @param size   大小
     * @return result
     */
    ResultDTO queryDetailCapitalFlow(Integer userId, Integer page, Integer size);

    /**
     * 提现
     *
     * @param userId
     * @param value  提现金额
     * @return
     */
    ResultDTO withdraw(Integer userId, Double value);
}
