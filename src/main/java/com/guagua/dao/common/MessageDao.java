package com.guagua.dao.common;

import com.guagua.bean.entity.common.Message;

import java.util.List;

/**
 * @author ride
 * @date 18-3-18 下午12:50
 */
public interface MessageDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条消息通知
     *
     * @param message message
     * @return result
     */
    Integer insertOne(Message message);


    /* ************************************************
     * update
     *************************************************/

    /**
     * 将消息更新为已读
     *
     * @param id id
     * @return result
     */
    Integer updateReadById(Integer id);

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据用户id查询用户的消息记录
     *
     * @param userId user id
     * @return result
     */
    List<Message> findAllByUserId(Integer userId);

    /**
     * 查询最近的五条消息记录
     *
     * @param userId user id
     * @return result
     */
    List<Message> findByUserIdLIMIT5(Integer userId);
}
