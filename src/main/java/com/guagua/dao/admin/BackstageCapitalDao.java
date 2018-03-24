package com.guagua.dao.admin;

import com.guagua.bean.entity.admin.BackstageCapital;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-24 上午11:58
 * <p>
 * 维护后台资金
 */
public interface BackstageCapitalDao {

    /* ************************************************
     * insert
     *************************************************/

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新资金
     *
     * @param id
     * @param value
     * @return
     */
    Integer updateOne(@Param("id") Integer id, @Param("value") Double value);

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有
     *
     * @return
     */
    List<BackstageCapital> findAll();

    /**
     * 根据id查询单条记录
     *
     * @param id
     * @return
     */
    BackstageCapital findById(@Param("id") Integer id);
}
