package com.guagua.service.member;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-24 下午8:01
 * <p>
 * 用户操作任务的服务
 */
public interface MemberTaskService {

    /**
     * 会员用户浏览所有任务, 任务类型为1(已发布投标的)
     *
     * @param userId
     * @return
     */
    ResultDTO listAll(Integer userId, Integer page, Integer size);

    /**
     * 查看任务详情
     *
     * @param userId
     * @param taskId
     * @return
     */
    ResultDTO queryDetail(Integer userId, Integer taskId);

    /**
     * 会员申请任务, 竞标
     *
     * @param userId
     * @param taskId
     * @return
     */
    ResultDTO applicationTask(Integer userId, Integer taskId);
}
