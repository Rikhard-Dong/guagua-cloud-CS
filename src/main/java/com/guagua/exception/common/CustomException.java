package com.guagua.exception.common;

import com.guagua.enums.DataDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-12 下午1:03
 * <p>
 * 自定义的异常类, 其他自定义的异常类都继承这个类
 */
public class CustomException extends RuntimeException {

    private DataDictionary msg;

    private Map<String, Object> data;

    public CustomException(DataDictionary msg) {
        super(msg.getMsg());
        this.msg = msg;
        data = new HashMap<String, Object>();
    }

    public CustomException(DataDictionary msg, Map<String, Object> data) {
        super(msg.getMsg());
        this.msg = msg;
        this.data = data;
    }


    public DataDictionary getMsg() {
        return msg;
    }

    public void setMsg(DataDictionary msg) {
        this.msg = msg;
    }

    public CustomException addData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
