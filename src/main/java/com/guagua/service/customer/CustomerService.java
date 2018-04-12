package com.guagua.service.customer;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.member.SatisfactionEvaluation;

/**
 * @author ride
 * @date 18-4-8 下午7:24
 */
public interface CustomerService {

    /**
     * 普通用户得到服务客服的id
     *
     * @param taskId task id
     * @return result dto
     */
    ResultDTO obtainCustomerService(Integer taskId);

    /**
     * 接入任务
     *
     * @param taskId task Id
     * @return result dto
     */
    ResultDTO accessTask(Integer taskId);

    /**
     * 给客服评分
     *
     * @param evaluation evaluation
     * @return result dto
     */
    ResultDTO evaluateCS(SatisfactionEvaluation evaluation);
}
