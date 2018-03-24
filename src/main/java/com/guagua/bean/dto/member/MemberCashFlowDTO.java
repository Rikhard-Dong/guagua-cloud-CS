package com.guagua.bean.dto.member;

import com.guagua.bean.entity.member.MemberCashFlow;
import com.guagua.utils.DateUtils;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午8:20
 */
public class MemberCashFlowDTO {

    private Integer id;             // 主键
    private Integer propertyId;     // 对应财产表id
    private Double value;           // 整数表示收入/充值, 负数标书支出/提现
    private String type;           // 类型: 对于会员用户来说只有0 收入, 1 提现
    private String time;              // 明细时间
    private String detail;      // 明细
    private Double balance;

    public MemberCashFlowDTO() {
    }

    public MemberCashFlowDTO(MemberCashFlow flow) {
        this.id = flow.getId();
        this.propertyId = flow.getPropertyId();
        this.value = flow.getValue();
        switch (flow.getType()) {
            case 0:
                this.type = "收入";
                break;
            case 1:
                this.type = "提现";
                break;
            case 2:
                this.type = "支出";
                break;
            case 3:
                this.type = "重置";
                break;
            default:
                this.type = "异常";
        }
        this.time = DateUtils.date2StrCN(flow.getTime());
        this.balance = flow.getBalance();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
        return "MemberCashFlowDTO{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", detail='" + detail + '\'' +
                ", balance=" + balance +
                '}';
    }
}
