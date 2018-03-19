package com.guagua.bean.entity.member;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午7:20
 * <p>
 * 用户财产信息
 */
public class MemberProperty {

    private Integer id;
    private Integer userId;
    private Double balance;
    private Double withdrawCashTotal;
    private Double incomeTotal;
    private Date createTime;
    private Date lastUpdateTime;


    public MemberProperty() {
    }

    public MemberProperty(Integer userId) {
        this.userId = userId;
        this.balance = 0.0;
        this.withdrawCashTotal = 0.0;
        this.incomeTotal = 0.0;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWithdrawCashTotal() {
        return withdrawCashTotal;
    }

    public void setWithdrawCashTotal(Double withdrawCashTotal) {
        this.withdrawCashTotal = withdrawCashTotal;
    }

    public Double getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(Double incomeTotal) {
        this.incomeTotal = incomeTotal;
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
}
