package com.guagua.service.admin.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.admin.HandleEnterpriseAuthentication;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.common.UserRole;
import com.guagua.bean.entity.enterprise.EnterpriseAuthentication;
import com.guagua.dao.admin.HandleEnterpriseAuthenticationDao;
import com.guagua.dao.common.MessageDao;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.common.UserRoleDao;
import com.guagua.dao.enterprise.EnterpriseAuthenticationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.enums.RoleConstant;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.admin.HandleEnterpriseAuthenticationService;
import com.guagua.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ride
 * @date 18-3-17 下午10:26
 * <p>
 * 实现类
 */
@Service("handleEnterpriseAuthenticationService")
public class HandleEnterpriseAuthenticationServiceImpl
        extends BaseService implements HandleEnterpriseAuthenticationService {

    private final UserDao userDao;

    private final EnterpriseAuthenticationDao enterpriseDao;

    private final HandleEnterpriseAuthenticationDao handleDao;

    private final MessageDao messageDao;

    private final UserRoleDao userRoleDao;

    @Autowired
    public HandleEnterpriseAuthenticationServiceImpl(UserDao userDao, EnterpriseAuthenticationDao enterpriseDao, HandleEnterpriseAuthenticationDao handleDao, MessageDao messageDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.enterpriseDao = enterpriseDao;
        this.handleDao = handleDao;
        this.messageDao = messageDao;
        this.userRoleDao = userRoleDao;
    }

    public ResultDTO handleAuthentication(Integer userId, Integer applyId,
                                          boolean result, String comment) {
        // 判断用户是否存在且是否有权利处理
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        if (!RoleUtils.hasRole(userId, "auditor")) {
            throw new CustomException(DataDictionary.NO_PERMISSION);

        }

        // 判断认证是否存在且认证状态为0 未审核
        EnterpriseAuthentication auth = enterpriseDao.findById(applyId);

        if (auth == null) {
            throw new CustomException(DataDictionary.AUTH_NOT_FOUND);
        }
        if (auth.getStatus() != 0) {
            // 认证状态错误
            throw new CustomException(DataDictionary.AUTHENTICATION_STATUS_ERROR);
        }

        if (result) {
            // TODO 审核通过
            // 更新审核状态
            Integer var1 = enterpriseDao.updateStatusById(applyId, 1);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 插入一条处理结果
            HandleEnterpriseAuthentication var2 = new HandleEnterpriseAuthentication(applyId, auth.getUserId(), user.getId(),
                    false, comment);
            Integer var3 = handleDao.insertOne(var2);
            if (var3 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }

            // 插入一条消息通知
            Message message = new Message(null, auth.getUserId(),
                    "[系统通知] 实名认证审核通过!", "审核通过!" + comment);
            Integer var4 = messageDao.insertOne(message);
            if (var4 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }

            // 查询该该用户的未认证会员用户角色关联信息
            UserRole userRole1 = userRoleDao.findByUserIdAndRoleId(auth.getUserId(),
                    RoleConstant.UNCERTIFIED_ENTERPRISE.getCode());
            Integer var5 = userRoleDao.deleteById(userRole1.getId());
            if (var5 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 新插入角色
            UserRole userRole2 = new UserRole(auth.getUserId(), RoleConstant.ENTERPRISE);
            Integer var6 = userRoleDao.insertUserRole(userRole2);
            if (var6 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            return new ResultDTO(DataDictionary.HANDLE_AUTH_SUCCESS);
        } else {
            // TODO 审核不通过
            // 更新认证状态
            Integer var1 = enterpriseDao.updateStatusById(applyId, 2);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 插入一套处理结果
            HandleEnterpriseAuthentication var2 = new HandleEnterpriseAuthentication(applyId,
                    auth.getUserId(), user.getId(), false, comment);
            Integer var3 = handleDao.insertOne(var2);

            if (var3 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }

            Message message = new Message(null, auth.getUserId(),
                    "[系统通知] 实名认证审核未通过!", "审核不通过!" + comment);
            Integer var5 = messageDao.insertOne(message);
            // 插入一条消息
            if (var5 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }


            return new ResultDTO(DataDictionary.HANDLE_AUTH_SUCCESS);
        }
    }
}
