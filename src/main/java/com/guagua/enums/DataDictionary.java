package com.guagua.enums;

/**
 * @author ride
 * @date 18-3-12 下午1:19
 * <p>
 * 数据字典
 */
public enum DataDictionary {
    LOGIN_SUCCESS(101, "登录成功"),
    REGISTER_SUCCESS(111, "注册成功"),
    USER_REGISTER_PASSWORD_NOT_MATCH(112, "密码不一致"),
    USER_REGISTER_TYPE_ERROR(113, "用户注册类型错误"),
    USER_REGISTER_VALIDATE_CODE_NOT_MATCH(114, "验证码错误"),
    ALREADY_SEND_CODE(120, "验证码已发送"),
    ILLEGAL_PHONE(121, "非法手机号"),
    LEGAL_PHONE(122, "手机号可以使用"),
    ALREADY_REGISTER_PHONE(123, "手机号已经注册"),
    VERIFYING_CODE_REQUEST_FREQUENT(124, "验证码请求过于频繁"),
    UNKNOWN_ERROR(-1, "未知错误"),
    SQL_OPERATION_EXCEPTION(-2, "数据库操作异常");

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
