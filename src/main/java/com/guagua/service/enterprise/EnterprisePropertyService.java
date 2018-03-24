package com.guagua.service.enterprise;

import com.guagua.bean.dto.ResultDTO;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午10:19
 */
public interface EnterprisePropertyService {

    /**
     * 查询资产详情
     *
     * @param userId
     * @return
     */
    ResultDTO queryProperty(Integer userId);

    /**
     * 查询资金明细
     *
     * @param userId

     * @return
     */
    ResultDTO queryPropertyDetail(Integer userId, Date startDate, Date endDate);

    /**
     * 提现
     *
     * @param userId
     * @param value
     * @return
     */
    ResultDTO withdraw(Integer userId, Double value);

    /**
     * 充值
     *
     * @param userId
     * @param value
     * @return
     */
    ResultDTO recharge(Integer userId, Double value);
}
