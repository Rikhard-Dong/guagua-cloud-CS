package com.guagua.dao;

import com.guagua.bean.entity.TestEntiry;

/**
 * @author ride
 * @date 18-3-14 下午2:18
 * <p>
 * 测试用 Dao
 */
public interface TestDao {

    /**
     * 添加一个测试
     *
     * @param entiry 实体
     * @return 结果
     */
    Integer insertTest(TestEntiry entiry);
}
