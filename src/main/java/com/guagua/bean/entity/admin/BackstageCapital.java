package com.guagua.bean.entity.admin;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-24 上午11:50
 * <p>
 * 后台资金总数. 企业提交任务. 扣除企业任务费用, 增加到此表.会员完成任务后将从此表中减去费用, 转存到用户账户
 */
public class BackstageCapital {
    private Integer id;
    private Double total;
    private Date lastUpdateTime;

    public BackstageCapital() {
    }

    public BackstageCapital(Integer id, Double total) {
        this.id = id;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "BackstageCapital{" +
                "id=" + id +
                ", total=" + total +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
