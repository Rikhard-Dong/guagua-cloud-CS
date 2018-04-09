package com.guagua.dao.common;

import com.guagua.bean.entity.common.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 更新用户头像
     *
     * @param userId    用户id
     * @param headImage 用户头像
     * @return 更新行数
     */
    Integer updateHeadImageByUserId(@Param("userId") Integer userId,
                                    @Param("headImage") String headImage);

    /**
     * 更新用户名
     *
     * @param userId
     * @param username
     * @return
     */
    Integer updateUsernameByUserId(@Param("userId") Integer userId,
                                   @Param("username") String username);

    /**
     * 更新用户性别
     *
     * @param userId
     * @param sex
     * @return
     */
    Integer updateSexByUserId(@Param("userId") Integer userId,
                              @Param("sex") Integer sex);

    /**
     * 更新用户qq
     *
     * @param userId
     * @param qq
     * @return
     */
    Integer updateQQByUserId(@Param("userId") Integer userId,
                             @Param("qq") String qq);

    /**
     * 更新用户微信
     *
     * @param userId
     * @param wechat
     * @return
     */
    Integer updateWechatByUserId(@Param("userId") Integer userId,
                                 @Param("wechat") String wechat);

    /**
     * 更新用户地址
     *
     * @param userId
     * @param address
     * @return
     */
    Integer updateAddressByUserId(@Param("userId") Integer userId,
                                  @Param("address") String address);

    /**
     * 更新用户描述
     *
     * @param userId
     * @param description
     * @return
     */
    Integer updateDescriptionByUserId(@Param("userId") Integer userId,
                                      @Param("description") String description);

    /**
     * 更新用户教育经历
     *
     * @param userId
     * @param educational
     * @return
     */
    Integer updateEducationalByUserId(@Param("userId") Integer userId,
                                      @Param("educational") String educational);

    /**
     * 更新用户邮箱
     *
     * @param userId user id
     * @param email  用户邮箱地址
     * @return 更新行数
     */
    Integer updateEmail(@Param("userId") Integer userId,
                        @Param("email") String email);

    Integer updateDeleteStatus(Integer userId);

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
     * 根据用户邮箱查找用户
     *
     * @param email 用户邮箱
     * @return user
     */
    User findByEmail(String email);

    /**
     * 查询t_user 表中最大id值
     *
     * @return 最大的id
     */
    Integer findMaxId();

    /**
     * 根据用户类型查找用户
     *
     * @param type
     * @return
     */
    List<User> findByType(Integer type);


}
