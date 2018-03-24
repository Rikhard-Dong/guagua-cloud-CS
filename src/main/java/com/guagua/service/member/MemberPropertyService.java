package com.guagua.service.member;

import com.guagua.bean.dto.ResultDTO;

import java.util.Date;

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
     * @return result
     */
    ResultDTO queryDetailCapitalFlow(Integer userId, Date startDate, Date endDate);

    /**
     * 提现
     *
     * @param userId
     * @param value  提现金额
     * @return
     */
    ResultDTO withdraw(Integer userId, Double value);
}
