package com.guagua.service.enterprise.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.common.TaskApplicationDTO;
import com.guagua.bean.dto.common.UserDTO;
import com.guagua.bean.entity.admin.BackstageCapital;
import com.guagua.bean.entity.admin.BackstageCashFlow;
import com.guagua.bean.entity.common.*;
import com.guagua.bean.entity.enterprise.BindTaskKnowledge;
import com.guagua.bean.entity.enterprise.EnterpriseAuthentication;
import com.guagua.bean.entity.enterprise.EnterpriseCashFlow;
import com.guagua.bean.entity.enterprise.EnterpriseProperty;
import com.guagua.bean.entity.member.MemberCashFlow;
import com.guagua.bean.entity.member.MemberProperty;
import com.guagua.dao.admin.BackstageCapitalDao;
import com.guagua.dao.admin.BackstageCashFlowDao;
import com.guagua.dao.common.*;
import com.guagua.dao.enterprise.BindTaskKnowledgeDao;
import com.guagua.dao.enterprise.EnterpriseAuthenticationDao;
import com.guagua.dao.enterprise.EnterpriseCashFlowDao;
import com.guagua.dao.enterprise.EnterprisePropertyDao;
import com.guagua.dao.member.MemberCashFlowDao;
import com.guagua.dao.member.MemberPropertyDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.enterprise.ReleaseTaskService;
import com.guagua.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-23 下午7:59
 */
@Service("releaseTaskService")
public class ReleaseTaskServiceImpl extends BaseService implements ReleaseTaskService {
    // 任务dao
    private final TaskDao taskDao;
    // 用户dao
    private final UserDao userDao;
    // 企业财产dao
    private final EnterprisePropertyDao enterprisePropertyDao;
    // 企业资金流水dao
    private final EnterpriseCashFlowDao enterpriseCashFlowDao;
    // 企业认证dao
    private final EnterpriseAuthenticationDao enterpriseAuthenticationDao;
    // 后台资金dao
    private final BackstageCapitalDao capitalDao;
    // 后台流水dao
    private final BackstageCashFlowDao cashFlowDao;
    // 任务申请关系dao
    private final TaskApplicationDao taskApplicationDao;
    // 消息dao
    private final MessageDao messageDao;
    // 任务雇佣关系dao
    private final TaskEmploymentDao taskEmploymentDao;
    // 会员财产dao
    private final MemberPropertyDao memberPropertyDao;
    // 会员资金流dao
    private final MemberCashFlowDao memberCashFlowDao;
    // 绑定任务和知识库
    private final BindTaskKnowledgeDao knowledgeDao;

    @Autowired
    public ReleaseTaskServiceImpl(TaskDao taskDao,
                                  UserDao userDao,
                                  EnterprisePropertyDao enterprisePropertyDao,
                                  EnterpriseCashFlowDao enterpriseCashFlowDao,
                                  EnterpriseAuthenticationDao enterpriseAuthenticationDao,
                                  BackstageCapitalDao capitalDao,
                                  BackstageCashFlowDao cashFlowDao,
                                  TaskApplicationDao taskApplicationDao,
                                  MessageDao messageDao,
                                  TaskEmploymentDao taskEmploymentDao,
                                  MemberPropertyDao memberPropertyDao,
                                  MemberCashFlowDao memberCashFlowDao,
                                  BindTaskKnowledgeDao knowledgeDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.enterprisePropertyDao = enterprisePropertyDao;
        this.enterpriseCashFlowDao = enterpriseCashFlowDao;
        this.enterpriseAuthenticationDao = enterpriseAuthenticationDao;
        this.capitalDao = capitalDao;
        this.cashFlowDao = cashFlowDao;
        this.taskApplicationDao = taskApplicationDao;
        this.messageDao = messageDao;
        this.taskEmploymentDao = taskEmploymentDao;
        this.memberPropertyDao = memberPropertyDao;
        this.memberCashFlowDao = memberCashFlowDao;
        this.knowledgeDao = knowledgeDao;
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
        List<TaskEmployment> taskEmployments = taskEmploymentDao.findByTaskId(taskId);
        // 计算需要支付的违约金
        Double liquidatedDamages = 0.0;
        if (taskEmployments != null || taskEmployments.size() != 0) {
            liquidatedDamages = task.getBaseSalary() * 0.1 * taskEmployments.size();
            Double liquidatedDamages2 = task.getBaseSalary() * 0.1;
            for (TaskEmployment employment : taskEmployments) {
                MemberProperty property = memberPropertyDao.findByUserId(employment.getMemberId());
                property.setBalance(property.getBalance() + liquidatedDamages2);
                property.setIncomeTotal(property.getIncomeTotal() + liquidatedDamages2);
                Integer var1 = memberPropertyDao.update(property);
                if (var1 == null) {
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
                MemberCashFlow flow = new MemberCashFlow(property.getId(), liquidatedDamages2, 0,
                        "任务#" + task.getTitle() + "#取消, 得到违约金!", property.getBalance());
                Integer var2 = memberCashFlowDao.insertOne(flow);
                if (var2 == 0) {
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }

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


        // 记录支付违约金
        if (liquidatedDamages != 0) {
            // 支付违约金
            enterpriseProperty.setBalance(enterpriseProperty.getBalance() - liquidatedDamages);
            Integer var5 = enterprisePropertyDao.updateOne(enterpriseProperty);
            if (var5 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
            EnterpriseCashFlow enterpriseCashFlow2 = new EnterpriseCashFlow(enterpriseProperty.getId(), -liquidatedDamages,
                    "任务#" + task.getTitle() + "#取消, 支付违约金" + liquidatedDamages, 2, enterpriseProperty.getBalance());
            Integer var6 = enterpriseCashFlowDao.insertOne(enterpriseCashFlow2);
            if (var6 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }
        Integer var5 = cashFlowDao.insertOne(backstageCashFlow);
        if (var5 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 通知所有参加竞标的. 状态为0和1的用户, 任务被取消
        List<TaskApplication> applications = taskApplicationDao.findByTaskId(taskId);
        if (applications != null && applications.size() != 0) {
            for (TaskApplication application : applications) {
                Message message = new Message();
                message.setSenderId(user.getId());
                message.setReceiverId(application.getMemberId());
                message.setTitle("任务取消通知");
                if (application.getStatus() == 0) {
                    message.setContent("任务#" + task.getTitle() + "#已经取消!");
                } else if (application.getStatus() == 1) {
                    message.setContent("任务#" + task.getTitle() + "#已经取消! 企业支付违约金, 已到账!");
                } else {
                    continue;
                }
                Integer var6 = messageDao.insertOne(message);
                if (var6 == 0) {
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 查询单个任务的竞标申请情况
    public ResultDTO queryTaskApplicationWithTaskId(Integer userId, Integer taskId, Integer page, Integer size) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<TaskApplication> applications = taskApplicationDao.findByTaskId(taskId);
        List<TaskApplicationDTO> dtos = convert2TaskApplicationDTO(applications);
        PageInfo<TaskApplicationDTO> info = new PageInfo<TaskApplicationDTO>(dtos);

        return new ResultDTO(DataDictionary.OPERATION_SUCCESS).addData("applications", info);
    }

    public ResultDTO queryAllUntreatedApplication(Integer userId, Integer taskId, Integer page, Integer size) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<TaskApplication> applications = taskApplicationDao.findUntreatedByTaskId(taskId);
        List<TaskApplicationDTO> dtos = convert2TaskApplicationDTO(applications);
        PageInfo<TaskApplicationDTO> info = new PageInfo<TaskApplicationDTO>(dtos);

        return new ResultDTO(DataDictionary.OPERATION_SUCCESS).addData("applications", info);
    }

    // 同意申请
    @Transactional
    public ResultDTO agreeApplication(Integer userId, Integer taskId, Integer applicationId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Task task = taskDao.findByTaskId(taskId);
        if (task == null || task.getStatus() != 1) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        TaskApplication taskApplication = taskApplicationDao.findById(applicationId);
        if (taskApplication == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        TaskEmployment employment = taskEmploymentDao.findByTaskIdAndMemberId(taskId, taskApplication.getMemberId());
        if (employment != null) {
            return new ResultDTO(DataDictionary.INSERT_SUCCESS);
        }

        // TODO 判断客服是否时间冲突

        // 申请通过
        taskApplication.setStatus(1);
        Integer var1 = taskApplicationDao.updateStatus(taskApplication.getId(), taskApplication.getStatus());
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 建立任务与用户的竞标成功联系
        TaskEmployment taskEmployment = new TaskEmployment(taskId, user.getId(), taskApplication.getMemberId());
        Integer var2 = taskEmploymentDao.insertOne(taskEmployment);
        if (var2 == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 发消息告诉用户竞标成功
        Message message = new Message(user.getId(), taskApplication.getMemberId(),
                "任务竞标成功通知", "您于" + DateUtils.date2StrCN(taskApplication.getCreateTime()) +
                "参与任务\"" + task.getTitle() + "\"的投标, 已被发起者同意申请!请前往我的任务查看详情");
        Integer var3 = messageDao.insertOne(message);
        if (var3 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        // 查询当前同意该任务的人数, 如果达到任务要求, 则更新任务状态为2(投标结束, 等待开始)
        Integer countNumber = taskEmploymentDao.countNumber(task.getId());
        if (task.getNumber().equals(countNumber)) {
            task.setStatus(2);
            Integer var4 = taskDao.updateStatus(taskId, task.getStatus());
            if (var4 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }

            // 获取所有状态为0的申请, 将状态改为2
            List<TaskApplication> applications = taskApplicationDao.findByTaskIdAndStatus(taskId, 0);
            if (applications != null && applications.size() > 0) {
                for (TaskApplication application : applications) {
                    application.setStatus(2);
                    taskApplicationDao.updateStatus(application.getId(), application.getStatus());
                    Message message1 = new Message(userId, application.getMemberId(), "申请失败",
                            "任务#" + task.getTitle() + "#人数已满, 您未能竞标成功");
                }
            }
        }
        return new ResultDTO(DataDictionary.OPERATION_SUCCESS);
    }

    // 拒绝申请
    @Transactional
    public ResultDTO refuseApplication(Integer userId, Integer taskId, Integer applicationId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        TaskApplication taskApplication = taskApplicationDao.findById(applicationId);
        if (taskApplication == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 申请不通过
        taskApplication.setStatus(2);
        Integer var1 = taskApplicationDao.updateStatus(taskApplication.getId(), taskApplication.getStatus());
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 发送消息通知会员用户竞标被拒绝
        Message message = new Message(user.getId(), taskApplication.getMemberId(),
                "任务竞标失败通知", "您于" + DateUtils.date2StrCN(taskApplication.getCreateTime()) +
                "参与任务\"" + task.getTitle() + "\"的投标, 已被发起者拒绝!");
        Integer var2 = messageDao.insertOne(message);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.OPERATION_SUCCESS);
    }

    // 查询任务对应的客服
    public ResultDTO getMyCustomerService(Integer userId, Integer taskId) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        List<User> users = taskEmploymentDao.findCustomerServiceByTaskId(taskId);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("customerService", convert2UserDTO(users));
    }

    // 查询该企业下建立过联系的所有客服
    public ResultDTO getAllCustomerService(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<User> users = taskEmploymentDao.findAllCustomerServiceByEnterpriseId(userId);
        List<UserDTO> dtos = convert2UserDTO(users);
        PageInfo<UserDTO> info = new PageInfo<UserDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("customerService", info);
    }

    // 绑定任务和知识库
    public ResultDTO bindKnowledge(Integer userId, Integer taskId, Integer knowledgeId) {
        BindTaskKnowledge knowledge = knowledgeDao.findByTaskIdAndKnowledge(taskId, knowledgeId);
        if (knowledge != null) {
            return new ResultDTO(DataDictionary.INSERT_SUCCESS);
        }

        knowledge = new BindTaskKnowledge(taskId, knowledgeId);
        Integer var = knowledgeDao.insertOne(knowledge);
        if (var == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 批量绑定任务和知识库
    public ResultDTO bindKnowledgeBatch(Integer userId, Integer taskId, String knowledgeIds) {
        String[] ids = knowledgeIds.split("-");
        List<BindTaskKnowledge> bindTaskKnowledges = new ArrayList<BindTaskKnowledge>();
        for (String id : ids) {
            Integer knowledgeId = Integer.valueOf(id);
            // 数据库中如果已经数据则跳过本次
            BindTaskKnowledge knowledge = knowledgeDao.findByTaskIdAndKnowledge(taskId, knowledgeId);
            if (knowledge != null) {
                continue;
            }
            knowledge = new BindTaskKnowledge(taskId, knowledgeId);
            bindTaskKnowledges.add(knowledge);
        }
        // 如果一个都没有的话, 插入会报错
        if (bindTaskKnowledges.size() > 0) {
            Integer var1 = knowledgeDao.insertBatch(bindTaskKnowledges);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
            }
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    /**
     * convert to TaskApplicationDTO
     *
     * @param applications
     * @return
     */
    private List<TaskApplicationDTO> convert2TaskApplicationDTO(List<TaskApplication> applications) {
        if (applications == null || applications.size() == 0) {
            return null;
        }

        List<TaskApplicationDTO> dtos = new ArrayList<TaskApplicationDTO>();

        for (TaskApplication application : applications) {
            TaskApplicationDTO dto = new TaskApplicationDTO(application);
            Task task = taskDao.findByTaskId(application.getTaskId());
            User user = userDao.findById(application.getMemberId());
            dto.setTaskName(task.getTitle());
            dto.setMemberName(user.getUsername());
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * convert to userDTO
     *
     * @param users
     * @return
     */
    private List<UserDTO> convert2UserDTO(List<User> users) {
        if (users == null || users.size() == 0) {
            return null;
        }
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        for (User user : users) {
            UserDTO dto = new UserDTO(user);
            dtos.add(dto);
        }

        return dtos;
    }
}
