package com.guagua.service.common.impl;

import com.guagua.bean.entity.admin.BackstageCapital;
import com.guagua.bean.entity.admin.BackstageCashFlow;
import com.guagua.bean.entity.common.*;
import com.guagua.bean.entity.enterprise.EnterpriseCashFlow;
import com.guagua.bean.entity.enterprise.EnterpriseProperty;
import com.guagua.bean.entity.member.MemberCashFlow;
import com.guagua.bean.entity.member.MemberProperty;
import com.guagua.dao.admin.BackstageCapitalDao;
import com.guagua.dao.admin.BackstageCashFlowDao;
import com.guagua.dao.common.*;
import com.guagua.dao.enterprise.EnterpriseCashFlowDao;
import com.guagua.dao.enterprise.EnterprisePropertyDao;
import com.guagua.dao.member.MemberCashFlowDao;
import com.guagua.dao.member.MemberPropertyDao;
import com.guagua.dao.member.SatisfactionEvaluationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-12 下午6:24
 * <p>
 * 实现类
 */
@Service("timeTaskService")
public class TimerTaskServiceImpl extends BaseService implements TimerTaskService {

    private final PhoneValidateCodeDao phoneCodeDao;
    private final EmailValidateCodeDao mailCodeDao;
    private final TaskDao taskDao;
    private final TaskApplicationDao applicationDao;
    private final TaskEmploymentDao employmentDao;
    private final MessageDao messageDao;
    private final EnterprisePropertyDao enterprisePropertyDao;
    private final EnterpriseCashFlowDao enterpriseCashFlowDao;
    private final MemberPropertyDao memberPropertyDao;
    private final MemberCashFlowDao memberCashFlowDao;
    private final BackstageCapitalDao capitalDao;
    private final BackstageCashFlowDao backstageCashFlowDao;
    private final SatisfactionEvaluationDao evaluationDao;

    @Autowired
    public TimerTaskServiceImpl(PhoneValidateCodeDao codeDao,
                                EmailValidateCodeDao mailCodeDao,
                                TaskDao taskDao,
                                TaskApplicationDao applicationDao,
                                TaskEmploymentDao employmentDao,
                                MessageDao messageDao,
                                EnterprisePropertyDao enterprisePropertyDao,
                                EnterpriseCashFlowDao enterpriseCashFlowDao,
                                MemberPropertyDao memberPropertyDao,
                                MemberCashFlowDao memberCashFlowDao,
                                BackstageCapitalDao capitalDao,
                                BackstageCashFlowDao backstageCashFlowDao,
                                SatisfactionEvaluationDao evaluationDao) {
        this.phoneCodeDao = codeDao;
        this.mailCodeDao = mailCodeDao;
        this.taskDao = taskDao;
        this.applicationDao = applicationDao;
        this.employmentDao = employmentDao;
        this.messageDao = messageDao;
        this.enterprisePropertyDao = enterprisePropertyDao;
        this.enterpriseCashFlowDao = enterpriseCashFlowDao;
        this.memberPropertyDao = memberPropertyDao;
        this.memberCashFlowDao = memberCashFlowDao;
        this.capitalDao = capitalDao;
        this.backstageCashFlowDao = backstageCashFlowDao;
        this.evaluationDao = evaluationDao;
    }

    // 清除短信验证码
    @Transactional
    public void clearTablePhoneValidateCodeData() {
//        logger.info("#################clear table t_phone_validate_code data####################");

        List<PhoneValidateCode> codes = phoneCodeDao.findAll();

        for (PhoneValidateCode code : codes) {
            if (code.isExpire()) {
                Integer flag = phoneCodeDao.deleteById(code.getId());
                if (flag == 0) {
                    logger.warn("删除过期PhoneValidateCode失败 ===> {}", code);
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }
    }

    // 清除手机验证码
    @Transactional
    public void clearTableMailValidateCodeData() {
//        logger.info("#################clear table t_email_validate_code data####################");

        List<EmailValidateCode> codes = mailCodeDao.findAll();
        for (EmailValidateCode code : codes) {
            if (code.isExpire()) {
                Integer flag = mailCodeDao.deleteEmailValidateCodeById(code.getId());
                if (flag == 0) {
                    logger.warn("删除过期EMailValidateCode失败 ===> {}", code);
                    throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
                }
            }
        }

    }

    /**
     * 更新任务的状态
     */
    @Transactional
    public void updateTaskStatus() {
        Date curDate = new Date();
        // 获取所有状态1的任务(任务竞标中), 更新状态为5(任务取消)
        List<Task> tasks1 = taskDao.findByStatus(1);
        if (tasks1 != null && tasks1.size() != 0) {
            for (Task task : tasks1) {
                // 如果已经开始
                if (curDate.after(task.getTaskStartTime())) {
                    // 任务取消
                    task.setStatus(5);
                    taskDao.updateStatus(task.getId(), task.getStatus());
                    // 获取所有状态为0 的申请
                    List<TaskApplication> applications = applicationDao.findByTaskIdAndStatus(task.getId(), 0);
                    if (applications != null && applications.size() > 0) {
                        for (TaskApplication application : applications) {
                            application.setStatus(3);
                            applicationDao.updateStatus(application.getId(), application.getStatus());
                            Message message = new Message(application.getPublisherId(), application.getMemberId(),
                                    "竞标失败", "任务#" + task.getTitle() + "#已取消, 发布者未处理您的申请, 竞标失败");
                            messageDao.insertOne(message);
                        }
                    }
                    // 获取所有的已经建立好的雇佣关系
                    List<TaskEmployment> employments = employmentDao.findByTaskId(task.getId());
                    Double liquidatedDamages = 0.0;
                    if (employments != null && employments.size() > 0) {
                        liquidatedDamages = task.getBaseSalary() * 0.1 * employments.size();
                        for (TaskEmployment employment : employments) {
                            // 后台资金相应减少
                            BackstageCapital capital = capitalDao.findById(1);
                            capital.setTotal(capital.getTotal() - task.getTotalSalary());
                            capitalDao.updateOne(capital.getId(), capital.getTotal());
                            BackstageCashFlow backstageCashFlow = new BackstageCashFlow(employment.getMemberId(),
                                    -(task.getBaseSalary() * 0.1), capital.getTotal(),
                                    "任务" + task.getTitle() + "取消, 企业支付违约金", 1);
                            backstageCashFlowDao.insertOne(backstageCashFlow);

                            // 任务状态更新为3
                            employment.setStatus(3);
                            employmentDao.updateStatus(employment.getId(), employment.getStatus());
                            // 会员用户增加金额
                            MemberProperty property = memberPropertyDao.findByUserId(employment.getMemberId());
                            property.setBalance(property.getBalance() + task.getBaseSalary() * 0.1);
                            property.setIncomeTotal(property.getIncomeTotal() + task.getBaseSalary() * 0.1);
                            memberPropertyDao.update(property);

                            // 记录流水
                            MemberCashFlow flow = new MemberCashFlow(property.getId(), task.getBaseSalary() * 0.1, 0,
                                    "任务#" + task.getTitle() + "#取消, 企业支付违约金", property.getBalance());
                            memberCashFlowDao.insertOne(flow);
                        }
                    }
                    // 后台资金相应减少
                    BackstageCapital capital = capitalDao.findById(1);
                    capital.setTotal(capital.getTotal() - task.getTotalSalary());
                    capitalDao.updateOne(capital.getId(), capital.getTotal());
                    BackstageCashFlow backstageCashFlow = new BackstageCashFlow(task.getEnterpriseId(),
                            -(task.getTotalSalary() - liquidatedDamages), capital.getTotal(),
                            "任务" + task.getTitle() + "取消, 资金退回", 1);
                    backstageCashFlowDao.insertOne(backstageCashFlow);
                    // 退还企业资金
                    EnterpriseProperty property = enterprisePropertyDao.findByUserId(task.getEnterpriseId());
                    property.setBalance(property.getBalance() + task.getTotalSalary() - liquidatedDamages);
                    enterprisePropertyDao.updateOne(property);
                    EnterpriseCashFlow flow = new EnterpriseCashFlow(property.getId(), task.getTotalSalary() - liquidatedDamages,
                            "任务#" + task.getTitle() + "#, 资金退回(已扣除违约金)", 4, property.getBalance());
                }
            }
        }

        // 获取所有状态2的任务(任务竞标结束, 未开始), 更新状态为3
        List<Task> tasks2 = taskDao.findByStatus(2);
        if (tasks2 != null && tasks2.size() != 0) {
            for (Task task : tasks2) {
                // 如果任务开始了
                if (curDate.after(task.getTaskStartTime())) {
                    task.setStatus(3);
                    taskDao.updateStatus(task.getId(), task.getStatus());
                    // 获取所有该任务的雇佣关系, 将状态修改为1, 任务进行中
                    List<TaskEmployment> employments = employmentDao.findByTaskId(task.getId());
                    if (employments != null && employments.size() != 0) {
                        for (TaskEmployment employment : employments) {
                            employment.setStatus(1);
                            employmentDao.updateStatus(employment.getId(), employment.getStatus());
                        }
                    }
                    // 获取该任务所有任务状态为0(申请未处理)的申请, 修改为申请状态为3(自动失效)
                    List<TaskApplication> applications = applicationDao.findByTaskIdAndStatus(task.getId(), 0);
                    if (applications != null && applications.size() > 0) {
                        for (TaskApplication application : applications) {
                            application.setStatus(3);
                            applicationDao.updateStatus(application.getId(), application.getStatus());
                            Message message = new Message(application.getPublisherId(), application.getMemberId(),
                                    "竞标失败", "任务#" + task.getTitle() + "#已开始, 发布者未处理您的申请, 竞标失败");
                            messageDao.insertOne(message);
                        }
                    }
                }
            }
        }

        // 获取所有状态3的任务(任务进行中), 更新状态为4(任务结束)
        List<Task> tasks3 = taskDao.findByStatus(3);
        if (tasks3 != null && tasks3.size() != 0) {
            for (Task task : tasks3) {
                // 任务结束
                if (task.getTaskEndTime().before(curDate)) {
                    task.setStatus(4);
                    taskDao.updateStatus(task.getId(), task.getStatus());
                    // 获取该任务的所有雇佣记录
                    List<TaskEmployment> employments = employmentDao.findByTaskId(task.getId());
                    if (employments != null || employments.size() != 0) {
                        for (TaskEmployment employment : employments) {
                            // 任务完成, 更新雇佣关系状态
                            employment.setStatus(2);
                            employmentDao.updateStatus(employment.getId(), employment.getStatus());

                            Integer nums = evaluationDao.countEvaluateNum(employment.getTaskId(), employment.getMemberId());
                            Integer sum = evaluationDao.sumOfScore(employment.getTaskId(), employment.getMemberId());
                            if (nums != null && nums > 0) {
                                Double customerAvgScore = (sum * 1.0 / nums);
                                Integer var1 = employmentDao.updateScore(employment.getId(), customerAvgScore);
                                if (var1 == 0) {
                                    logger.error("update task employment customer avg score error! employment id ===> {}",
                                            employment.getId());
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
