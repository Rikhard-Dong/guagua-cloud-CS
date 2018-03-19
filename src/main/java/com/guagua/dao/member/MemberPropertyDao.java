package com.guagua.dao.member;

import com.guagua.bean.entity.member.MemberProperty;
import org.apache.ibatis.annotations.Param;

/**
 * @author ride
 * @date 18-3-19 下午7:28
 * <p>
 * 会员用户财产记录
 */
public interface MemberPropertyDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条数据
     *
     * @param property
     * @return
     */
    Integer insertOne(MemberProperty property);


    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新一条数据
     *
     * @param property
     * @return
     */
    Integer update(@Param("property") MemberProperty property);

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据用户id查询个人财产信息
     *
     * @param userId
     * @return
     */
    MemberProperty findByUserId(Integer userId);


}
