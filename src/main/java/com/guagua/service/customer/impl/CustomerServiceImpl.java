package com.guagua.service.customer.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.TaskEmployment;
import com.guagua.dao.common.TaskDao;
import com.guagua.dao.common.TaskEmploymentDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.customer.CustomerService;
import com.guagua.singleton.MemberSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public CustomerServiceImpl(TaskDao taskDao, TaskEmploymentDao employmentDao) {
        this.taskDao = taskDao;
        this.employmentDao = employmentDao;
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
        List<TaskEmployment> employments = employmentDao.findByTaskId(taskId);

        logger.info("employments ======>  {}", employments);

        Integer accessTaskId = null;

        Integer min = Integer.MAX_VALUE;

        // 找到当前接入数量最少的客服
        for (Map.Entry<Integer, Integer> entry : singleton.getMemberAccesses().entrySet()) {
            if (employments.contains(entry.getKey())) {
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
}
