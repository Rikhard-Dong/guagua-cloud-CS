package com.guagua.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.member.MemberCashFlowDTO;
import com.guagua.bean.dto.member.MemberPropertyDTO;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.member.MemberCashFlow;
import com.guagua.bean.entity.member.MemberProperty;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.member.MemberCashFlowDao;
import com.guagua.dao.member.MemberPropertyDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.member.MemberPropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.print.CUPSPrinter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-19 下午7:47
 */
@Service("memberPropertyService")
public class MemberPropertyServiceImpl extends BaseService implements MemberPropertyService {

    private final UserDao userDao;

    private final MemberPropertyDao propertyDao;

    private final MemberCashFlowDao flowDao;

    @Autowired
    public MemberPropertyServiceImpl(MemberPropertyDao propertyDao, UserDao userDao, MemberCashFlowDao flowDao) {
        this.propertyDao = propertyDao;
        this.userDao = userDao;
        this.flowDao = flowDao;
    }

    // 查询基本信息
    @Transactional
    public ResultDTO queryProperty(Integer userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        MemberProperty var = propertyDao.findByUserId(user.getId());
        if (var == null) {
            var = new MemberProperty(user.getId());
            propertyDao.insertOne(var);
        }
        MemberPropertyDTO dto = new MemberPropertyDTO(var);
        dto.setUsername(user.getUsername());

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("property", dto);
    }

    // 查询资金流水细则
    public ResultDTO queryDetailCapitalFlow(Integer userId, Date startTime, Date endTime) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        MemberProperty property = propertyDao.findByUserId(user.getId());
        if (property == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        List<MemberCashFlow> flows = flowDao.findByPropertyIdAndDate(property.getId(), startTime, endTime);
        List<MemberCashFlowDTO> dtos = convert2MemberCashFlowDTO(flows);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("flows", dtos);
    }

    // 提现
    @Transactional
    public ResultDTO withdraw(Integer userId, Double value) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        MemberProperty property = propertyDao.findByUserId(user.getId());
        if (property == null) {
            // 如果为空则创建
            property = new MemberProperty(user.getId());
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

        Integer var2 = propertyDao.update(property);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 记录收支明细
        value = 0 - value;
        MemberCashFlow flow = new MemberCashFlow(property.getId(), value, 1, "提现操作", property.getBalance());
        Integer var3 = flowDao.insertOne(flow);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.WITHDRAW_CASH_SUCCESS);
    }

    /**
     * 将flow 转换为dto
     *
     * @param flows
     * @return
     */
    private List<MemberCashFlowDTO> convert2MemberCashFlowDTO(List<MemberCashFlow> flows) {
        if (flows == null || flows.size() == 0) {
            return null;
        }
        List<MemberCashFlowDTO> dtos = new ArrayList<MemberCashFlowDTO>();
        for (MemberCashFlow flow : flows) {
            MemberCashFlowDTO dto = new MemberCashFlowDTO(flow);
            dtos.add(dto);
        }
        return dtos;
    }
}
