package com.guagua.service.enterprise.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.admin.BackstageCapital;
import com.guagua.bean.entity.admin.BackstageCashFlow;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.enterprise.EnterpriseAuthentication;
import com.guagua.bean.entity.enterprise.EnterpriseCashFlow;
import com.guagua.bean.entity.enterprise.EnterpriseProperty;
import com.guagua.dao.admin.BackstageCapitalDao;
import com.guagua.dao.admin.BackstageCashFlowDao;
import com.guagua.dao.common.TaskDao;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.enterprise.EnterpriseAuthenticationDao;
import com.guagua.dao.enterprise.EnterpriseCashFlowDao;
import com.guagua.dao.enterprise.EnterprisePropertyDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.enterprise.ReleaseTaskService;
import com.mysql.fabric.xmlrpc.base.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-23 下午7:59
 */
@Service("releaseTaskService")
public class ReleaseTaskServiceImpl extends BaseService implements ReleaseTaskService {

    private final TaskDao taskDao;

    private final UserDao userDao;

    private final EnterprisePropertyDao enterprisePropertyDao;

    private final EnterpriseCashFlowDao enterpriseCashFlowDao;

    private final EnterpriseAuthenticationDao enterpriseAuthenticationDao;

    private final BackstageCapitalDao capitalDao;

    private final BackstageCashFlowDao cashFlowDao;

    @Autowired
    public ReleaseTaskServiceImpl(TaskDao taskDao, UserDao userDao, EnterprisePropertyDao enterprisePropertyDao, EnterpriseCashFlowDao enterpriseCashFlowDao, EnterpriseAuthenticationDao enterpriseAuthenticationDao, BackstageCapitalDao capitalDao, BackstageCashFlowDao cashFlowDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.enterprisePropertyDao = enterprisePropertyDao;
        this.enterpriseCashFlowDao = enterpriseCashFlowDao;
        this.enterpriseAuthenticationDao = enterpriseAuthenticationDao;
        this.capitalDao = capitalDao;
        this.cashFlowDao = cashFlowDao;
    }

    // 创建发布一个任务
    @Transactional
    public ResultDTO createTask(Integer userId, Task task) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        if (task.getTaskStartTime() == null) {
            throw new CustomException(DataDictionary.REQUIRED_PARAMETER_IS_NULL);
        }
        if (task.getTaskStartTime().before(new Date())) {
            throw new CustomException(DataDictionary.DATE_ERROR);
        }
        if (task.getTaskEndTime() == null) {
            throw new CustomException(DataDictionary.REQUIRED_PARAMETER_IS_NULL);
        }
        if (task.getTaskStartTime().after(task.getTaskEndTime())) {
            throw new CustomException(DataDictionary.DATE_ERROR);
        }

        task.setTotalSalary((task.getBaseSalary() + task.getBonus()) * task.getNumber());
        task.setEnterpriseId(user.getId());

        EnterpriseProperty enterpriseProperty = enterprisePropertyDao.findByUserId(user.getId());
        if (enterpriseProperty == null) {
            enterpriseProperty = new EnterpriseProperty(user.getId());
            Integer var1 = enterprisePropertyDao.insertOne(enterpriseProperty);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }

        if (enterpriseProperty.getBalance() < task.getTotalSalary()) {
            throw new CustomException(DataDictionary.BALANCE_NOT_ENOUGH);
        }
        Integer var0 = taskDao.insertOne(task);
        if (var0 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 扣除企业花费, 并且记录流水
        enterpriseProperty.setBalance(enterpriseProperty.getBalance() - task.getTotalSalary());
        enterpriseProperty.setPayTotal(enterpriseProperty.getPayTotal() + task.getTotalSalary());
        Integer var1 = enterprisePropertyDao.updateOne(enterpriseProperty);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        EnterpriseCashFlow enterpriseCashFlow = new EnterpriseCashFlow(enterpriseProperty.getId(),
                -task.getTotalSalary(), "发布任务: " + task.getTitle(), 2,
                enterpriseProperty.getBalance());
        Integer var2 = enterpriseCashFlowDao.insertOne(enterpriseCashFlow);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 增加后台金额, 记录后台流水
        BackstageCapital capital = capitalDao.findById(1);
        capital.setTotal(capital.getTotal() + task.getTotalSalary());
        Integer var3 = capitalDao.updateOne(capital.getId(), capital.getTotal());
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        EnterpriseAuthentication authentication = enterpriseAuthenticationDao.findByEnterpriseIdAndPass(user.getId());
        if (authentication == null) {
            // 未认证状态
            throw new CustomException(DataDictionary.AUTHENTICATION_STATUS_ERROR);
        }
        BackstageCashFlow backstageCashFlow = new BackstageCashFlow(user.getId(), task.getTotalSalary(),
                capital.getTotal(), authentication.getEnterpriseName() + "发起任务 : " + task.getTitle(), 0);

        Integer var4 = cashFlowDao.insertOne(backstageCashFlow);
        if (var4 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 查询所有的任务
    public ResultDTO queryTask(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<Task> tasks = taskDao.findByEnterpriseId(user.getId());
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("tasks", info);
    }

    /**
     * 根据任务状态查询任务
     *
     * @param userId
     * @param status
     * @param page
     * @param size
     * @return
     */
    public ResultDTO queryTaskByStatus(Integer userId, Integer status, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<Task> tasks = taskDao.findByEnterpriseIdAndStatus(user.getId(), status);
        PageInfo<Task> info = new PageInfo<Task>(tasks);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("tasks", info);
    }

    /**
     * 查询详细
     *
     * @param userId
     * @param id
     * @return
     */
    public ResultDTO queryDetail(Integer userId, Integer id) {
        Task task = taskDao.findByTaskId(id);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("task", task);
    }

    // 取消一个任务
    @Transactional
    public ResultDTO cancelTask(Integer userId, Integer taskId) {
        // 判断用户和任务是否存在
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 任务发布者与任务取消者需要是同一个人
        if (!user.getId().equals(task.getEnterpriseId())) {
            throw new CustomException(DataDictionary.USER_NOT_MATCH);
        }

        // 任务只有在状态1(投标中)和状态2(投标结束但是未开始)这两种情况下才能取消任务
        if (!(task.getStatus() == 1 || task.getStatus() == 2)) {
            throw new CustomException(DataDictionary.TASK_CAN_NOT_CANCEL);
        }

        // TODO 任务被取消需要通知参与投标的客服, 如果用户已中标, 需要支付10%的违约金
        // TODO 当前先完成更新任务状态, 将钱退还到企业, 更新资金流水
        // 更新任务状态
        Integer var1 = taskDao.updateStatus(task.getId(), 5);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 将企业资金退还
        EnterpriseProperty enterpriseProperty = enterprisePropertyDao.findByUserId(user.getId());
        if (enterpriseProperty == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        enterpriseProperty.setBalance(enterpriseProperty.getBalance() + task.getTotalSalary());
        Integer var2 = enterprisePropertyDao.updateOne(enterpriseProperty);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        EnterpriseCashFlow enterpriseCashFlow = new EnterpriseCashFlow(enterpriseProperty.getId(),
                task.getTotalSalary(), "任务#" + task.getTitle() + "#取消, 退回资金", 4,
                enterpriseProperty.getBalance());
        Integer var3 = enterpriseCashFlowDao.insertOne(enterpriseCashFlow);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 后台资金相应减少
        BackstageCapital capital = capitalDao.findById(1);
        capital.setTotal(capital.getTotal() - task.getTotalSalary());
        Integer var4 = capitalDao.updateOne(capital.getId(), capital.getTotal());
        if (var4 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        BackstageCashFlow backstageCashFlow = new BackstageCashFlow(user.getId(), -task.getTotalSalary(), capital.getTotal(),
                "任务" + task.getTitle() + "取消, 资金退回", 1);
        Integer var5 = cashFlowDao.insertOne(backstageCashFlow);
        if (var5 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }
}
