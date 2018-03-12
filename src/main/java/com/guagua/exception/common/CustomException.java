package com.guagua.exception.common;

import com.guagua.enums.DataDictionary;

/**
 * @author ride
 * @date 18-3-12 下午1:03
 * <p>
 * 自定义的异常类, 其他自定义的异常类都继承这个类
 */
public class CustomException extends RuntimeException {

    private DataDictionary msg;

    public CustomException(DataDictionary msg) {
        super(msg.getMsg());
        this.msg = msg;
    }

    public DataDictionary getMsg() {
        return msg;
    }

    public void setMsg(DataDictionary msg) {
        this.msg = msg;
    }
}
