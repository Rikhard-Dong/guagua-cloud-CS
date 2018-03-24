package com.guagua.bean.entity.admin;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-24 上午11:55
 * <p>
 * 后台流水情况
 */
public class BackstageCashFlow {

    private Integer id;
    private Integer objectId;   // 发起本次流水的用户id
    private Double value;
    private Double total;
    private String detail;
    private Integer type;
    private Date time;

    public BackstageCashFlow() {
    }

    public BackstageCashFlow(Integer objectId, Double value, Double total, String detail, Integer type) {
        this.objectId = objectId;
        this.value = value;
        this.total = total;
        this.detail = detail;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "BackstageCashFlow{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", value=" + value +
                ", total=" + total +
                ", detail='" + detail + '\'' +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
