package com.guagua.bean.dto;

import com.guagua.enums.DataDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-12 下午1:38
 */
public class ResultDto {
    private Integer code;
    private String msg;

    private Map<String, Object> data;

    public ResultDto() {
        this.data = new HashMap<String, Object>();
    }

    public ResultDto(DataDictionary dataDictionary) {
        this.code = dataDictionary.getCode();
        this.msg = dataDictionary.getMsg();
        this.data = new HashMap<String, Object>();
    }

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = new HashMap<String, Object>();
    }

    public ResultDto addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public void setCodeAndMsg(DataDictionary dataDictionary) {
        this.code = dataDictionary.getCode();
        this.msg = dataDictionary.getMsg();
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
