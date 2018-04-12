package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.dao.common.*;
import com.guagua.dao.question.QuestionBankDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.DataStatisticsService;
import com.guagua.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ride
 * @date 18-4-11 上午8:42
 */
@Service("dataStatisticsService")
public class DataStatisticsServiceImpl extends BaseService implements DataStatisticsService {

    private final UserDao userDao;

    private final TaskDao taskDao;

    private final KnowledgeBaseDao baseDao;

    private final QuestionBankDao bankDao;

    private final TaskApplicationDao applicationDao;

    private final TaskEmploymentDao employmentDao;


    @Autowired
    public DataStatisticsServiceImpl(UserDao userDao,
                                     TaskDao taskDao,
                                     KnowledgeBaseDao baseDao,
                                     QuestionBankDao bankDao,
                                     TaskApplicationDao applicationDao,
                                     TaskEmploymentDao employmentDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;
        this.baseDao = baseDao;
        this.bankDao = bankDao;
        this.applicationDao = applicationDao;
        this.employmentDao = employmentDao;
    }


    /* ************************************
     * admin data statistics
     *
     **************************************/

    // get admin data summary
    public ResultDTO adminSummary() {
        Date startTime;
        Date endTime = new Date();
        try {
            startTime = DateUtils.str2Date("2000-1-1 0:0:0");
        } catch (ParseException e) {
            throw new CustomException(DataDictionary.ERROR);
        }

        return adminSummary(startTime, endTime);
    }

    // get admin summary by time
    public ResultDTO adminSummaryByTime(Date startTime, Date endTime) {
        return adminSummary(startTime, endTime);
    }


    /**
     * @param startTime start time
     * @param endTime   end time
     * @return admin summary result dto
     */
    private ResultDTO adminSummary(Date startTime, Date endTime) {
        Map<String, Object> userDataSummary = new HashMap<String, Object>();
        Map<String, Object> taskDataSummary = new HashMap<String, Object>();

        userDataSummary.put("total", userDao.countUserNum(startTime, endTime));
        userDataSummary.put("admin_total", userDao.countUserNumByUserType(0, startTime, endTime));
        userDataSummary.put("enterprise_total", userDao.countUserNumByUserType(1, startTime, endTime));
        userDataSummary.put("member_total", userDao.countUserNumByUserType(2, startTime, endTime));
        userDataSummary.put("delete_user_total", userDao.countIsDeleteUserNum(startTime, endTime));

        taskDataSummary.put("total", taskDao.countNum(startTime, endTime));
        taskDataSummary.put("tendering_task", taskDao.countNumByStatus(1, startTime, endTime));
        taskDataSummary.put("completion_of_tender_task", taskDao.countNumByStatus(2, startTime, endTime));
        taskDataSummary.put("progress_task", taskDao.countNumByStatus(3, startTime, endTime));
        taskDataSummary.put("end_task", taskDao.countNumByStatus(4, startTime, endTime));
        taskDataSummary.put("cancel_task", taskDao.countNumByStatus(5, startTime, endTime));

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("user_summary", userDataSummary)
                .addData("task_summary", taskDataSummary);
    }

    /* ************************************
     * enterprise data statistics
     *
     **************************************/

    // 数据统计信息
    public ResultDTO enterpriseSummary(Integer userId) {

        Integer baseNum = baseDao.countNums(userId);
        Integer bankNum = bankDao.countNUms(userId);

        Map<String, Object> taskSummary = new HashMap<String, Object>();

        taskSummary.put("total", taskDao.countNumByUserId(userId));
        taskSummary.put("tendering_task", taskDao.countNumByStatusAndUserId(userId, 1));
        taskSummary.put("completion_of_tender_task", taskDao.countNumByStatusAndUserId(userId, 2));
        taskSummary.put("progress_task", taskDao.countNumByStatusAndUserId(userId, 3));
        taskSummary.put("end_task", taskDao.countNumByStatusAndUserId(userId, 4));
        taskSummary.put("cancel_task", taskDao.countNumByStatusAndUserId(userId, 5));

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("knowledge_base_total", baseNum)
                .addData("question_bank_total", bankNum)
                .addData("task_summary", taskSummary);
    }

    // 查询
    public ResultDTO enterpriseSummaryByTime(Date startTime, Date endTime) {
        // TODO 暂时不需要
        return null;
    }


    /* ************************************
     * member data statistics
     *
     **************************************/

    // 统计信息
    public ResultDTO memberSummary(Integer userId) {

        Integer applicationTotal = applicationDao.countTaskByStatus(userId, null);
        Integer applicationUntreatedTotal = applicationDao.countTaskByStatus(userId, 0);
        Integer applicationSuccessTotal = applicationDao.countTaskByStatus(userId, 1);
        Integer applicationFailTotal = applicationTotal - applicationUntreatedTotal - applicationSuccessTotal;

        Integer taskTotal = employmentDao.countNumByMemberAndStatus(userId, null);
        Integer taskNotStartTotal = employmentDao.countNumByMemberAndStatus(userId, 0);
        Integer taskHaveInHandTotal = employmentDao.countNumByMemberAndStatus(userId, 1);
        Integer taskEndTotal = employmentDao.countNumByMemberAndStatus(userId, 2);
        Integer taskCancelTotal = employmentDao.countNumByMemberAndStatus(userId, 3);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("application_total", applicationTotal)
                .addData("application_untreated_total", applicationUntreatedTotal)
                .addData("application_success_total", applicationSuccessTotal)
                .addData("application_fail_total", applicationFailTotal)
                .addData("task_total", taskTotal)
                .addData("task_not_start_total", taskNotStartTotal)
                .addData("task_have_in_hand_total", taskHaveInHandTotal)
                .addData("task_end_total", taskEndTotal)
                .addData("task_cancel_total", taskCancelTotal);
    }
}
