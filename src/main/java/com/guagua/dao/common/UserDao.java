package com.guagua.dao.common;

import com.guagua.bean.entity.common.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一个用户
     *
     * @param user 带插入的用户
     * @return 数据库操作结果
     */
    Integer insertUser(User user);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新密码
     *
     * @param phone    手机号
     * @param password 密码
     * @return 更新行数
     */
    Integer updatePassword(@Param("phone") String phone, @Param("password") String password);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 根据user id 删除用户
     *
     * @param id user id
     * @return 数据库操作结果
     */
    Integer deleteById(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 验证账号是否可以登录
     *
     * @param account  登录账号     // TODO 初步只能使用手机号登录
     * @param password 加密后的密码
     * @return 如果验证通过返回对应对象, 验证不通过就返回空
     */
    User validateAccount(@Param("account") String account, @Param("password") String password);

    /**
     * 根据id查询user
     *
     * @param id user id
     * @return 查询结果
     */
    User findById(Integer id);

    /**
     * 根据phone查询user, user的phone是unique的
     *
     * @param phone 手机号
     * @return 查询结果
     */
    User findByPhone(String phone);

    /**
     * 查询t_user 表中最大id值
     *
     * @return 最大的id
     */
    Integer findMaxId();
}
