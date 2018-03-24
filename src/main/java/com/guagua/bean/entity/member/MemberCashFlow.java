package com.guagua.bean.entity.member;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午7:23
 * <p>
 * 会员用户资金流明细
 */
public class MemberCashFlow {
    private Integer id;             // 主键
    private Integer propertyId;     // 对应财产表id
    private Double value;           // 整数表示收入/充值, 负数标书支出/提现
    private Integer type;           // 类型: 对于会员用户来说只有0 收入, 1 提现
    private Date time;              // 明细时间
    private String detail;          // 资金流明细
    private Double balance;         // 操作后的余额

    public MemberCashFlow() {
    }

    public MemberCashFlow(Integer propertyId, Double value, Integer type, String detail, Double balance) {
        this.propertyId = propertyId;
        this.value = value;
        this.type = type;
        this.detail = detail;
        this.balance = balance;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MemberCashFlow{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", value=" + value +
                ", type=" + type +
                ", time=" + time +
                ", detail='" + detail + '\'' +
                ", balance=" + balance +
                '}';
    }
}
