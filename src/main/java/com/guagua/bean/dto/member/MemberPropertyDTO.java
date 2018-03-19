package com.guagua.bean.dto.member;

import com.guagua.bean.entity.member.MemberProperty;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-19 下午8:05
 */
public class MemberPropertyDTO {
    private Integer id;                 // id
    private Integer userId;             // 用户id
    private String username;            // 用户名
    private Double balance;             // 余额
    private Double withdrawCashTotal;   // 提现总额
    private Double incomeTotal;         // 收入总额
    private String createTime;          // 创建时间
    private String lastUpdateTime;      // 最后更新时间

    public MemberPropertyDTO() {
    }

    public MemberPropertyDTO(MemberProperty property) {
        this.id = property.getId();
        this.userId = property.getUserId();
        this.balance = property.getBalance();
        this.withdrawCashTotal = property.getWithdrawCashTotal();
        this.incomeTotal = property.getIncomeTotal();
        this.createTime = DateUtils.date2StrCN(property.getCreateTime());
        this.lastUpdateTime = DateUtils.date2StrCN(property.getLastUpdateTime());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "MemberPropertyDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", withdrawCashTotal=" + withdrawCashTotal +
                ", incomeTotal=" + incomeTotal +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }
}
