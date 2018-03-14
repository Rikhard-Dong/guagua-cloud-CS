package com.guagua.dao.common;

import com.guagua.bean.entity.common.Menu;
import org.apache.ibatis.annotations.Param;

/**
 * @author ride
 * @date 18-3-14 下午3:28
 */
public interface MenuDao {

    /**
     * 插入一个菜单
     *
     * @param menu 菜单
     * @return 操作结果
     */
    Integer insertMenu(@Param("menu") Menu menu);
}
