package com.guagua.bean.entity.enterprise;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午9:58
 *
 */
public class EnterpriseProperty {
    private int id;
    private int userId;
    private Double balance;
    private Double rechargeTotal;           // 重置总额
    private Double withdrawCashTotal;       // 提现总额
    private Double payTotal;                // 支出总额
    private Date createTime;
    private Date lastUpdateTime;

    public EnterpriseProperty() {
    }

    public EnterpriseProperty(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getRechargeTotal() {
        return rechargeTotal;
    }

    public void setRechargeTotal(Double rechargeTotal) {
        this.rechargeTotal = rechargeTotal;
    }

    public Double getWithdrawCashTotal() {
        return withdrawCashTotal;
    }

    public void setWithdrawCashTotal(Double withdrawCashTotal) {
        this.withdrawCashTotal = withdrawCashTotal;
    }

    public Double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Double payTotal) {
        this.payTotal = payTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "EnterpriseProperty{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", rechargeTotal=" + rechargeTotal +
                ", withdrawCashTotal=" + withdrawCashTotal +
                ", payTotal=" + payTotal +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}

