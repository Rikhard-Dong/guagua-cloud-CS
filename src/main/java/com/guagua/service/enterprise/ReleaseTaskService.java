package com.guagua.service.enterprise;


import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Task;

/**
 * @author ride
 * @date 18-3-23 下午7:58
 */
public interface ReleaseTaskService {

    /**
     * 创建发布一个任务
     *
     * @param userId
     * @param task
     * @return
     */
    ResultDTO createTask(Integer userId, Task task);

    /**
     * 查询所有的任务
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    ResultDTO queryTask(Integer userId, Integer page, Integer size);

    /**
     * 根据任务状态查询任务
     *
     * @param userId
     * @param status
     * @param page
     * @param size
     * @return
     */
    ResultDTO queryTaskByStatus(Integer userId, Integer status, Integer page, Integer size);

    /**
     * 查询单条的详细记录
     *
     * @param userId
     * @param id
     * @return
     */
    ResultDTO queryDetail(Integer userId, Integer id);

    /**
     * 取消任务发布
     *
     * @param userId
     * @param taskId
     * @return
     */
    ResultDTO cancelTask(Integer userId, Integer taskId);
}
