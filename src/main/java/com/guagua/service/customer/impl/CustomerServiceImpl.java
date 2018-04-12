package com.guagua.service.customer.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.TaskEmployment;
import com.guagua.bean.entity.member.SatisfactionEvaluation;
import com.guagua.dao.common.TaskDao;
import com.guagua.dao.common.TaskEmploymentDao;
import com.guagua.dao.member.SatisfactionEvaluationDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.customer.CustomerService;
import com.guagua.singleton.MemberSingleton;
import com.mysql.fabric.xmlrpc.base.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ride
 * @date 18-4-8 下午7:24
 */

@Service("customerService")
public class CustomerServiceImpl extends BaseService implements CustomerService {


    private MemberSingleton singleton = MemberSingleton.getInstance();

    private final TaskDao taskDao;

    private final TaskEmploymentDao employmentDao;

    private final SatisfactionEvaluationDao evaluationDao;

    @Autowired
    public CustomerServiceImpl(TaskDao taskDao,
                               TaskEmploymentDao employmentDao,
                               SatisfactionEvaluationDao evaluationDao) {
        this.taskDao = taskDao;
        this.employmentDao = employmentDao;
        this.evaluationDao = evaluationDao;
    }

    // 获取在线客服id
    public ResultDTO obtainCustomerService(Integer taskId) {
        Task task = taskDao.findByTaskId(taskId);

        logger.info("online members =====> {}", singleton.getOnlineMembers());
        logger.info("member access ======> {}", singleton.getMemberAccesses());

        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        if (task.getStatus() != 3) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "任务没有在进行中");
        }

        // TODO 判断客服是否在工作时间


        List<TaskEmployment> employments = employmentDao.findByTaskId(taskId);

        List<Integer> taskMemberIds = new ArrayList<Integer>();
        for (TaskEmployment employment : employments) {
            taskMemberIds.add(employment.getMemberId());
        }

        logger.info("employments ======>  {}", employments);

        Integer accessTaskId = null;

        Integer min = Integer.MAX_VALUE;

        // 找到当前接入数量最少的客服
        for (Map.Entry<Integer, Integer> entry : singleton.getMemberAccesses().entrySet()) {
            if (taskMemberIds.contains(entry.getKey())) {
                if (min > entry.getValue()) {
                    min = entry.getValue();
                    accessTaskId = entry.getKey();
                }
            }
        }

        if (accessTaskId == null) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "当前没有客服在线");
        }

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("cs_id", accessTaskId);
    }

    // 普通用户接入任务
    public ResultDTO accessTask(Integer taskId) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        Boolean success = task.getStatus() == 3;
        String detail = task.getStatus() == 3 ? "任务进行中" : "任务不在进行中";

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("taskId", taskId)
                .addData("flag", success)
                .addData("detail", detail);
    }

    // 评价客服
    public ResultDTO evaluateCS(SatisfactionEvaluation evaluation) {

        Integer var1 = evaluationDao.insertOne(evaluation);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }
}
