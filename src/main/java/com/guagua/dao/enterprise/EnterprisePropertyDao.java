package com.guagua.dao.enterprise;

import com.guagua.bean.entity.enterprise.EnterpriseProperty;
import org.apache.ibatis.annotations.Param;

/**
 * @author ride
 * @date 18-3-19 下午10:08
 */
public interface EnterprisePropertyDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条资金流记录
     *
     * @param flow
     * @return
     */
    Integer insertOne(EnterpriseProperty flow);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新用户的财产信息
     * @param property
     * @return
     */
    Integer updateOne(@Param("property") EnterpriseProperty property);

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据用户id查询出property
     * @param userId
     * @return
     */
    EnterpriseProperty findByUserId(Integer userId);
}
