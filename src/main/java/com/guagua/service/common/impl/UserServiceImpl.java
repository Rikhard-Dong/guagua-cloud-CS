package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.dto.common.SimpleUserDTO;
import com.guagua.bean.dto.common.UserDTO;
import com.guagua.bean.entity.common.PhoneValidateCode;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.common.UserLoginLog;
import com.guagua.bean.entity.common.UserRole;
import com.guagua.dao.common.PhoneValidateCodeDao;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.common.UserLoginLogDao;
import com.guagua.dao.common.UserRoleDao;
import com.guagua.enums.DataDictionary;
import com.guagua.enums.RoleConstant;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.UserService;
import com.guagua.utils.*;
import com.qiniu.common.QiniuException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-12 下午2:37
 */

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    private final UserDao userDao;

    private final PhoneValidateCodeDao codeDao;

    private final UserLoginLogDao loginLogDao;

    private final UserRoleDao userRoleDao;

    @Autowired
    public UserServiceImpl(PhoneValidateCodeDao codeDao, UserDao userDao, UserLoginLogDao loginLogDao, UserRoleDao userRoleDao) {
        this.codeDao = codeDao;
        this.userDao = userDao;
        this.loginLogDao = loginLogDao;
        this.userRoleDao = userRoleDao;
    }


    // 判断手机号是否存在
    public ResultDto isPhoneExists(String phone) {
        User user = userDao.findByPhone(phone);
        return user == null ? new ResultDto(DataDictionary.LEGAL_PHONE) :
                new ResultDto(DataDictionary.ALREADY_REGISTER_PHONE);
    }

    // 发送验证码短信
    @Transactional
    public ResultDto sendSMSCode(String phone) throws CustomException {
        boolean flag = !RegExpUtils.isPhoneLegal(phone);
        if (flag) {
            // 输入的手机号不合法
            throw new CustomException(DataDictionary.UNREGISTER_PHONE);
        }

        PhoneValidateCode code = codeDao.findLatestPhoneValidateCodeByPhone(phone);
        if (code != null) {
            Date currTime = new Date();
            Date expireTime = new Date(code.getSendTime().getTime() + 60 * 1000);
            if (currTime.before(expireTime)) {
                // 请求过于频繁, 两次验证码发送间隔为1分钟
                throw new CustomException(DataDictionary.VERIFYING_CODE_REQUEST_FREQUENT);
            } else {
                codeDao.deleteById(code.getId());
            }

        }

        String codeStr = SMSCodeUtils.SendAlicomMsgDemo(phone);
        Integer flag2 = codeDao.insertPhoneValidateCode(new PhoneValidateCode(phone, codeStr));

        if (flag2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.ALREADY_SEND_CODE);
    }

    // 用户注册
    @Transactional
    public ResultDto register(User user, String code) throws CustomException {
        boolean flag = !RegExpUtils.isPhoneLegal(user.getPhone());
        if (flag) {
            // 输入的手机号不合法
            throw new CustomException(DataDictionary.UNREGISTER_PHONE);
        }
        User var1 = userDao.findByPhone(user.getPhone());
        if (var1 != null) {
            // 该手机号已经注册了
            throw new CustomException(DataDictionary.ALREADY_REGISTER_PHONE);
        }
        if (user.getType() != 1 && user.getType() != 2) {
            // 注册用户类型不正确 1. 企业用户  2. 会员用户
            throw new CustomException(DataDictionary.USER_TYPE_ERROR);
        }

        PhoneValidateCode var2 = codeDao.findLatestPhoneValidateCodeByPhone(user.getPhone());
        String var3 = var2 == null ? "" : var2.getCode();
        if (!StringUtils.equals(code, var3)) {
            // 验证码不匹配
            throw new CustomException(DataDictionary.VALIDATE_CODE_NOT_MATCH);
        }

        // 设置默认用户名
        Integer var4 = userDao.findMaxId();
        String username = "用户" + ((var4 == null ? 0 : var4) + 1);
        user.setUsername(username);

        Integer var5 = userDao.insertUser(user);
        if (var5 == 0) {
            // 用户插入失败
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 验证结束后从数据库中删除
        if (var2 != null) {
            codeDao.deleteById(var2.getId());
        }

        // 插入后返回插入的结果的自增id, 然后赋予对应的角色
        logger.info("######### user id =====> {} #######", user.getId());
        if (user.getId() == null) {
            throw new CustomException(DataDictionary.GET_AUTO_INC_ID_FAIL);
        }
        UserRole var6 = new UserRole(user.getId(), RoleConstant.BASE_USER);
        logger.info("###########user role =====> {} #####################", var6);
        userRoleDao.insertUserRole(var6);

        if (user.getType() == 1) {
            // 绑定企业用户关系
            UserRole var7 = new UserRole(user.getId(), RoleConstant.UNCERTIFIED_ENTERPRISE);
            Integer var8 = userRoleDao.insertUserRole(var7);
            if (var8 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        } else if (user.getType() == 2) {
            // 绑定会员用户关系
            UserRole var9 = new UserRole(user.getId(), RoleConstant.UNCERTIFIED_MEMBER);
            Integer var10 = userRoleDao.insertUserRole(var9);
            if (var10 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }

        return new ResultDto(DataDictionary.REGISTER_SUCCESS);
    }

    // 用户登录功能模块
    public ResultDto login(String account, String password, String loginIp) {

        // TODO 目前只允许用户用手机号登录
        User user = userDao.findByPhone(account);
        if (user == null) {
            logger.info("用户未注册");
            return new ResultDto(DataDictionary.ACCOUNT_OR_PASSWORD_ERROR);
        }

        String encryption = CryptographyUtils.md5(password, user.getSalt());
        logger.info("encryption ===> {}\n" +
                "password =====> {}", encryption, user.getPassword());
        if (StringUtils.equals(encryption, user.getPassword())) {
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("userId", user.getId());
            claims.put("phone", user.getPhone());
            String jwt = JWTUtils.createToken(claims);

            // 记录登录信息
            UserLoginLog log = new UserLoginLog(user.getId(), loginIp);
            loginLogDao.insertUserLoginLog(log);

            return new ResultDto(DataDictionary.LOGIN_SUCCESS)
                    .addData("token", jwt);
        } else {
            logger.info("密码错误");
            return new ResultDto(DataDictionary.ACCOUNT_OR_PASSWORD_ERROR);
        }

    }

    // 重置密码
    public ResultDto resetPassword(String phone, String code, String password, String repassword) {
        User user = userDao.findByPhone(phone);
        if (user == null) {
            // 手机号未注册, 不能重置密码
            return new ResultDto(DataDictionary.UNREGISTER_PHONE);
        }

        if (!StringUtils.equals(password, repassword)) {
            // 两次密码不一致
            return new ResultDto(DataDictionary.TOW_PASSWORD_NOT_MATCH);
        }

        PhoneValidateCode var1 = codeDao.findLatestPhoneValidateCodeByPhone(phone);
        if (!StringUtils.equals(var1.getCode(), code)) {
            return new ResultDto(DataDictionary.VALIDATE_CODE_NOT_MATCH);
        }

        // 加密密码
        String encryptionPassword = CryptographyUtils.md5(password, user.getSalt());
        Integer var2 = userDao.updatePassword(phone, encryptionPassword);

        if (var2 == 0) {
            return new ResultDto(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.UPDATE_PASSWORD_SUCCESS);
    }

    // 获取用户简单信息
    public ResultDto getSimpleUserInfo(Integer userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            // 抛出用户不存在异常
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        SimpleUserDTO var1 = new SimpleUserDTO(user);

        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("info", var1);
    }

    // 获取用户基本信息
    public ResultDto getUserInfo(Integer userId) {
        User user = isUserExists(userId);

        UserDTO var1 = new UserDTO(user);
        return new ResultDto(DataDictionary.QUERY_SUCCESS).addData("info", var1);
    }

    // 主动更新用户密码
    @Transactional
    public ResultDto updatePassword(Integer userId, String oldPassword, String password, String repassword) {
        logger.info(">>>>>>>>>>>>>> 更新密码 <<<<<<<<<<<<<<<<<");
        User user = isUserExists(userId);

        // 对旧密码进行加密
        String var1 = CryptographyUtils.md5(oldPassword, user.getSalt());
        if (!StringUtils.equals(var1, user.getPassword())) {
            // 旧密码不匹配
            return new ResultDto(DataDictionary.OLD_PASSWORD_NOT_MATCH);
        }

        if (!StringUtils.equals(password, repassword)) {
            // 两次密码不匹配
            return new ResultDto(DataDictionary.TOW_PASSWORD_NOT_MATCH);
        }

        // 加密新密码
        String var2 = CryptographyUtils.md5(password, user.getSalt());

        // 更新用户密码
        Integer var3 = userDao.updatePassword(user.getPhone(), var2);

        if (var3 == 0) {
            return new ResultDto(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新用户头像
    @Transactional
    public ResultDto updateHeadImage(Integer userId, String imgBase64) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        // 旧头像
        String oldImgUrl = user.getHeadImage();

        // 上传文件名
        String filename = "img/head/" + UUIDUtils.getUUID() + ".jpg";
        // TODO 无法知道七牛云是否上传成功
        try {
            QiniuUtils.uploadByBase64(imgBase64, filename);
        } catch (Exception e) {
            throw new CustomException(DataDictionary.IMG_UPLOAD_FAIL);
        }

        filename = "http://p5etjjbs6.bkt.clouddn.com/" + filename;
        Integer var1 = userDao.updateHeadImageByUserId(userId, filename);

        if (var1 == 0) {
            // 数据库更新用户头像失败
            try {
                QiniuUtils.deleteImg(filename);
            } catch (QiniuException e) {
                logger.error("更新用户头像失败");

                throw new CustomException(DataDictionary.IMG_DELETE_FAIL);
            }
        }


        oldImgUrl = StringUtils.removeAll(oldImgUrl, "http://p5etjjbs6.bkt.clouddn.com/");

        if (StringUtils.indexOf(oldImgUrl, "default") == -1) {
            try {
                QiniuUtils.deleteImg(oldImgUrl);
            } catch (QiniuException e) {
                logger.info(">>>>>>>>>>>>>>>>>>>删除图片 ===> {} 失败<<<<<<<<<<<<<<<<<<<<<<<<<", oldImgUrl);
                return new ResultDto(DataDictionary.QINIU_OPERATION_FAIL);
            }
        }

        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新用户名
    public ResultDto updateUsername(Integer userId, String username) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        Integer var1 = userDao.updateUsernameByUserId(userId, username);
        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新性别
    public ResultDto updateSex(Integer userId, Integer sex) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        // 性别必须是0或1 分别表示女和男
        if (sex != 0 && sex != 1) {
            throw new CustomException(DataDictionary.SEX_ILLEGAL);

        }

        Integer var1 = userDao.updateSexByUserId(userId, sex);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新qq
    public ResultDto updateQQ(Integer userId, String qq) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        // qq号不合法
        if (!RegExpUtils.isQQLegal(qq)) {
            throw new CustomException(DataDictionary.QQ_ILLEGAL);
        }

        Integer var1 = userDao.updateQQByUserId(userId, qq);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新微信
    public ResultDto updateWechat(Integer userId, String wechat) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        // 微信号不合法
        if (!RegExpUtils.isWecahtLegal(wechat)) {
            throw new CustomException(DataDictionary.WECHAT_ILLEGAL);
        }

        Integer var1 = userDao.updateWechatByUserId(userId, wechat);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新描述
    public ResultDto updateDescription(Integer userId, String description) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }
        Integer var1 = userDao.updateDescriptionByUserId(userId, description);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    // 更新教育经历
    public ResultDto updateEducational(Integer userId, String educational) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        Integer var1 = userDao.updateEducationalByUserId(userId, educational);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }

    public ResultDto updateAddress(Integer userId, String address) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new ResultDto(DataDictionary.USER_NOT_EXISTS);
        }

        Integer var1 = userDao.updateAddressByUserId(userId, address);

        if (var1 == 0) {
            return new ResultDto(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDto(DataDictionary.UPDATE_SUCCESS);
    }
}
