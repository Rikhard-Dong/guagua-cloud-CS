package com.guagua.service.enterprise.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.enterprise.EnterpriseCashFlowDTO;
import com.guagua.bean.dto.enterprise.EnterprisePropertyDTO;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.enterprise.EnterpriseCashFlow;
import com.guagua.bean.entity.enterprise.EnterpriseProperty;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.enterprise.EnterpriseCashFlowDao;
import com.guagua.dao.enterprise.EnterprisePropertyDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.enterprise.EnterprisePropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-19 下午10:20
 */
@Service("enterprisePropertyService")
public class EnterprisePropertyServiceImpl extends BaseService implements EnterprisePropertyService {

    private final UserDao userDao;

    private final EnterprisePropertyDao propertyDao;

    private final EnterpriseCashFlowDao flowDao;

    @Autowired
    public EnterprisePropertyServiceImpl(UserDao userDao, EnterprisePropertyDao propertyDao,
                                         EnterpriseCashFlowDao flowDao) {
        this.userDao = userDao;
        this.propertyDao = propertyDao;
        this.flowDao = flowDao;
    }

    // 查询资产
    public ResultDTO queryProperty(Integer userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        EnterpriseProperty property = propertyDao.findByUserId(userId);
        if (property == null) {
            property = new EnterpriseProperty(user.getId());
            Integer var1 = propertyDao.insertOne(property);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }

        EnterprisePropertyDTO dto = new EnterprisePropertyDTO(property);
        dto.setUsername(user.getUsername());
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("property", dto);
    }

    // 查询资金明细
    public ResultDTO queryPropertyDetail(Integer userId, Date startDate, Date endDate) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        EnterpriseProperty property = propertyDao.findByUserId(user.getId());
        if (property == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }


        List<EnterpriseCashFlow> flows = flowDao.findByPropertyIdAndDate(property.getId(), startDate, endDate);

        List<EnterpriseCashFlowDTO> flowsDto = convert2EnterpriseCashFlow(flows);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("flows", flowsDto);
    }

    // 提现
    public ResultDTO withdraw(Integer userId, Double value) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        EnterpriseProperty property = propertyDao.findByUserId(user.getId());
        if (property == null) {
            // 如果为空则创建
            property = new EnterpriseProperty(user.getId());
            Integer var1 = propertyDao.insertOne(property);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }
        if (property.getBalance() < value) {
            throw new CustomException(DataDictionary.BALANCE_NOT_ENOUGH);
        }
        // 提现操作
        property.setBalance(property.getBalance() - value);
        property.setWithdrawCashTotal(property.getWithdrawCashTotal() + value);

        Integer var2 = propertyDao.updateOne(property);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 记录收支明细
        value = 0 - value;
        EnterpriseCashFlow flow = new EnterpriseCashFlow(property.getId(), value, "提现操作", 1,
                property.getBalance());
        Integer var3 = flowDao.insertOne(flow);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.WITHDRAW_CASH_SUCCESS);
    }

    // 充值
    public ResultDTO recharge(Integer userId, Double value) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        EnterpriseProperty property = propertyDao.findByUserId(user.getId());
        if (property == null) {
            // 如果为空则创建
            property = new EnterpriseProperty(user.getId());
            Integer var1 = propertyDao.insertOne(property);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }

        // 重置操作
        property.setBalance(property.getBalance() + value);
        property.setRechargeTotal(property.getRechargeTotal() + value);

        Integer var2 = propertyDao.updateOne(property);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        EnterpriseCashFlow flow = new EnterpriseCashFlow(property.getId(), value, "充值", 3,
                property.getBalance());
        Integer var3 = flowDao.insertOne(flow);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.RECHARGE_SUCCESS);
    }

    private List<EnterpriseCashFlowDTO> convert2EnterpriseCashFlow(List<EnterpriseCashFlow> flows) {
        if (flows == null || flows.size() == 0) {
            return null;
        }
        List<EnterpriseCashFlowDTO> dtos = new ArrayList<EnterpriseCashFlowDTO>();
        for (EnterpriseCashFlow flow : flows) {
            EnterpriseCashFlowDTO dto = new EnterpriseCashFlowDTO(flow);
            dtos.add(dto);
        }

        return dtos;
    }
}
