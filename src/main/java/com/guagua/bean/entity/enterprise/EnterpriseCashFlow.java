package com.guagua.bean.entity.enterprise;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午10:04
 */
public class EnterpriseCashFlow {
    private Integer id;
    private Integer propertyId;
    private Double value;
    private String detail;
    private Integer type;       // 0 收入 1 提现 2 支出 3 充值
    private Date time;

    public EnterpriseCashFlow() {
    }

    public EnterpriseCashFlow(Integer propertyId, Double value, String detail, Integer type) {
        this.propertyId = propertyId;
        this.value = value;
        this.detail = detail;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "EnterpriseCashFlow{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", value=" + value +
                ", detail='" + detail + '\'' +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
