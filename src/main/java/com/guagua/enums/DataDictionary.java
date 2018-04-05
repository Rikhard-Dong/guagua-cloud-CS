package com.guagua.enums;

/**
 * @author ride
 * @date 18-3-12 下午1:19
 * <p>
 * 数据字典
 */
public enum DataDictionary {
    // 增删查改成功与失败
    INSERT_SUCCESS(1, "插入成功"),
    INSERT_FAIL(2, "插入失败"),
    UPDATE_SUCCESS(3, "更新成功"),
    UPDATE_FAIL(4, "更新失败"),
    DELETE_SUCCESS(5, "删除成功"),
    DELETE_FAIL(6, "删除失败"),
    QUERY_SUCCESS(7, "查询成功"),
    QUERY_FAIL(8, "查询失败"),

    BALANCE_NOT_ENOUGH(10, "余额不足"),
    WITHDRAW_CASH_SUCCESS(11, "提现成功"),
    RECHARGE_SUCCESS(12, "充值成功"),

    // 登录方面
    LOGIN_SUCCESS(101, "登录成功"),
    ACCOUNT_OR_PASSWORD_ERROR(102, "用户名或者密码不正确"),
    USER_NOT_EXISTS(103, "用户不存在"),

    // 注册方面
    REGISTER_SUCCESS(111, "注册成功"),
    TOW_PASSWORD_NOT_MATCH(112, "密码不一致"),
    USER_TYPE_ERROR(113, "用户注册类型错误"),
    VALIDATE_CODE_NOT_MATCH(114, "验证码错误"),

    UPDATE_PASSWORD_SUCCESS(115, "密码更新成功"),
    OLD_PASSWORD_NOT_MATCH(116, "旧密码不匹配"),
    SEX_ILLEGAL(117, "非法性别"),
    QQ_ILLEGAL(118, "非法qq号"),
    WECHAT_ILLEGAL(119, "非法微信号"),

    ALREADY_SEND_CODE(120, "验证码已发送"),
    UNREGISTER_PHONE(121, "未注册手机号"),
    LEGAL_PHONE(122, "手机号未注册"),
    ALREADY_REGISTER_PHONE(123, "手机号已经注册"),
    VERIFYING_CODE_REQUEST_FREQUENT(124, "验证码请求过于频繁"),
    EMAIL_IS_EXISTS(125, "邮箱已经存在"),
    SEND_EMAIL_CODE_SUCCESS(126, "发送邮件验证码成功"),
    SEND_EMAIL_CODE_FAIL(127, "发送邮件验证码失败"),
    SEND_EMAIL_USER_NOT_MATCH(128, "发送和验证邮件验证码的用户不一致"),
    BIND_EMAIL_SUCCESS(129, "绑定验证码成功"),
    BIND_EMAIL_FAIL(130, "绑定验证码失败"),
    VALIDATE_CODE_EXPIRE(131, "验证码过期"),
    QINIU_OPERATION_SUCCESS(132, "七牛云操作成功"),
    QINIU_OPERATION_FAIL(133, "七牛云操作失败"),

    // token方面
    REFRESH_TOKEN_SUCCESS(140, "令牌刷新成功"),
    REFRESH_TOKEN_FAIL(141, "令牌刷新失败"),
    TOKEN_EXPIRE(142, "令牌已过期"),
    INVALID_TOKEN(143, "无效令牌"),

    // 图片资源方面
    IMG_UPLOAD_SUCCESS(150, "图片上传成功"),
    IMG_UPLOAD_FAIL(151, "图片上传失败"),
    IMG_DELETE_SUCCESS(152, "图片删除成功"),
    IMG_DELETE_FAIL(153, "图片删除失败"),

    // 认证方面
    AUTHENTICATION_SUCCESS(160, "认证成功"),
    AUTHENTICATION_FAIL(161, "认证失败"),
    HAS_AUTHENTICATION(162, "已认证, 不论成功与否"),
    QUERY_AUTHENTICATION_SUCCESS(163, "查询认证信息成功"),
    QUERY_AUTHENTICATION_FAIL(164, "查询认证信息失败"),
    AUTHENTICATION_APPLY_SUCCESS(165, "认证申请成功"),
    AUTHENTICATION_APPLY_FAIL(166, "认证申请失败"),
    AUTHENTICATION_STATUS_ERROR(167, "认证状态错误"),
    AUTH_NOT_FOUND(168, "认证信息没有发现"),
    HANDLE_AUTH_SUCCESS(169, "处理认证信息成功"),

    // 错误方面
    UNKNOWN_ERROR(-1, "未知错误"),
    SQL_OPERATION_EXCEPTION(-2, "数据库操作异常"),
    GET_AUTO_INC_ID_FAIL(-3, "数据库错误, 获取自增Id失败"),
    AUTHORIZATION_FAIL(-4, "认证失败, 请重新登录"),
    NO_PERMISSION(-5, "权限不足"),
    STRING_CONVERT_DATE_FAIL(-6, "字符串转换日期失败"),
    REQUIRED_PARAMETER_IS_NULL(-7, "必填参数为空"),
    DATE_ERROR(-8, "日期参数错误"),
    USER_NOT_MATCH(-9, "用户不匹配"),
    TASK_CAN_NOT_CANCEL(-10, "任务不能取消"),
    TASK_NOT_EXISTS(-11, "任务不存在"),
    OPERATION_SUCCESS(16, "操作成功"),
    ALREADY_APPLICATION_THIS_TASK(-12, "已经参与过该任务的投标"),
    ALREADY_AGREE(-13, "已经同意, 无需再次同意"),
    ERROR(-100, "错误");

    private Integer code;       // 状态码
    private String msg;         // 描述信息

    DataDictionary(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
