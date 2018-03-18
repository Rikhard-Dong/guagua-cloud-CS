package com.guagua.service.admin.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.admin.HandleMemberAuthentication;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.common.UserRole;
import com.guagua.bean.entity.member.MemberAuthentication;
import com.guagua.dao.admin.HandleMemberAuthenticationDao;
import com.guagua.dao.common.MessageDao;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.common.UserRoleDao;
import com.guagua.dao.member.MemberAuthenticationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.enums.RoleConstant;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.admin.HandleMemberAuthenticationService;
import com.guagua.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ride
 * @date 18-3-17 下午10:24
 */
@Service("handleMemberAuthenticationService")
public class HandleMemberAuthenticationServiceImpl
        extends BaseService implements HandleMemberAuthenticationService {

    private final UserDao userDao;

    private final MemberAuthenticationDao memberDao;

    private final HandleMemberAuthenticationDao handleDao;

    private final MessageDao messageDao;

    private final UserRoleDao userRoleDao;

    @Autowired
    public HandleMemberAuthenticationServiceImpl(UserDao userDao, MemberAuthenticationDao memberDao, HandleMemberAuthenticationDao handleDao, MessageDao messageDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.memberDao = memberDao;
        this.handleDao = handleDao;
        this.messageDao = messageDao;
        this.userRoleDao = userRoleDao;
    }


    // 实名认证
    @Transactional
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
        MemberAuthentication auth = memberDao.findById(applyId);
        if (auth == null) {
            throw new CustomException(DataDictionary.AUTH_NOT_FOUND);
        }
        if (auth.getStatus() != 0) {
            // 认证状态错误
            throw new CustomException(DataDictionary.AUTHENTICATION_STATUS_ERROR);
        }

        if (result) {
            // 审核通过
            // 更新审核状态
            Integer var1 = memberDao.updateStatusById(applyId, 1);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 插入一条处理结果
            HandleMemberAuthentication var2 = new HandleMemberAuthentication(applyId, auth.getUserId(), user.getId(),
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
                    RoleConstant.UNCERTIFIED_MEMBER.getCode());
            Integer var5 = userRoleDao.deleteById(userRole1.getId());
            if (var5 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 新插入角色
            UserRole userRole2 = new UserRole(auth.getUserId(), RoleConstant.MEMBER);
            Integer var6 = userRoleDao.insertUserRole(userRole2);
            if (var6 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            return new ResultDTO(DataDictionary.HANDLE_AUTH_SUCCESS);
        } else {
            // 审核不通过
            // 更新认证状态
            Integer var1 = memberDao.updateStatusById(applyId, 2);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            // 插入一条处理结果
            HandleMemberAuthentication var2 = new HandleMemberAuthentication(applyId,
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
