package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;

import java.util.Map;


/**
 * @author ride
 * @date 18-3-18 下午3:27
 */
public interface MessageService {
    /**
     * 得到用户最近的五条记录
     *
     * @param userId 用户id
     * @return result
     */
    ResultDTO getLatestFive(Integer userId);

    /**
     * 分页查询所有消息
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    ResultDTO getAllMessage(Integer userId, Integer page, Integer size);

    /**
     * 获取用户所有已读的消息记录
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    ResultDTO getReadMessages(Integer userId, Integer page, Integer size);


    /**
     * 获取用户所有未读的消息记录
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    ResultDTO getUnreadMessages(Integer userId, Integer page, Integer size);

    /**
     * 统计未读消息的条数
     *
     * @param userId
     * @return
     */
    Map<String, Object> countUnreadMessage(Integer userId);
}
