package com.guagua.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.TaskApplication;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.*;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.member.MemberTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ride
 * @date 18-3-24 下午8:02
 * <p>
 * 会员用户任务处理相关处理
 */
@Service("memberTaskService")
public class MemberTaskServiceImpl extends BaseService implements MemberTaskService {
    private final UserDao userDao;

    private final MessageDao messageDao;

    private final TaskDao taskDao;

    private final TaskApplicationDao taskApplicationDao;

    private final TaskEmploymentDao taskEmploymentDao;


    @Autowired
    public MemberTaskServiceImpl(UserDao userDao,
                                 MessageDao messageDao,
                                 TaskDao taskDao,
                                 TaskApplicationDao taskApplicationDao,
                                 TaskEmploymentDao taskEmploymentDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.taskDao = taskDao;
        this.taskApplicationDao = taskApplicationDao;
        this.taskEmploymentDao = taskEmploymentDao;
    }

    // 会员查询所有可投标的任务
    public ResultDTO listAll(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Task> tasks = taskDao.findAllByMember();
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("tasks", info);
    }

    // 查询任务详情
    public ResultDTO queryDetail(Integer userId, Integer taskId) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        // TODO 只能查询任务状态为1(任务竞标中)和该用户参与的任务

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("task", task);
    }

    // 竞标
    @Transactional
    public ResultDTO applicationTask(Integer userId, Integer taskId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        // 任务的状态必须是1
        if (task.getStatus() != 1) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        // 需要判断本任务是否投过标了
        TaskApplication var3 = taskApplicationDao.findByTaskIdAndMemberId(task.getId(), user.getId());
        // 暂时对于单个任务, 会员用户只能申请一次, 如果申请被拒绝, 也不能重新申请
        if (var3 != null /*&& var3.getStatus() != 2*/) {
            throw new CustomException(DataDictionary.ALREADY_APPLICATION_THIS_TASK);
        }

        // TODO 需要判断时间是否冲突

        // 建立一个申请联系
        TaskApplication application = new TaskApplication(task.getId(), task.getEnterpriseId(), user.getId());
        Integer var1 = taskApplicationDao.insertOne(application);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        // 发送消息通知企业有新的申请
        Message message = new Message(user.getId(), task.getEnterpriseId(), "新的客服参与竞标, 请处理",
                "您的任务:" + task.getTitle() + "有新的客服参与了竞标, 请及时处理!");
        Integer var2 = messageDao.insertOne(message);
        if (var2 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.OPERATION_SUCCESS);
    }

    // 获取用户参与的所有任务
    public ResultDTO getParticipateAll(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<Task> tasks = taskEmploymentDao.findParticipateAllByUserId(userId);
        addEnterpriseInfo(tasks);
        PageInfo<Task> info = new PageInfo<Task>(tasks);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);
    }

    // 获取用户参与的未开始的所有任务
    public ResultDTO getParticipateNotStart(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        PageHelper.startPage(page, size);
        List<Task> tasks = taskEmploymentDao.findParticipateNotStartByUserId(userId);
        addEnterpriseInfo(tasks);
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);

    }


    // 获用户参与的所有已经开始的任务
    public ResultDTO getParticipateHaveInHand(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }


        PageHelper.startPage(page, size);
        List<Task> tasks = taskEmploymentDao.findParticipateHaveInHandByUserId(userId);
        addEnterpriseInfo(tasks);
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);
    }

    // 查询所有结束的任务
    public ResultDTO getParticipateEnd(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<Task> tasks = taskEmploymentDao.findParticipateEndByUserId(userId);
        addEnterpriseInfo(tasks);
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);
    }
    // 查询所有取消的任务

    public ResultDTO getParticipateCancel(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }

        PageHelper.startPage(page, size);
        List<Task> tasks = taskEmploymentDao.findParticipateCancelByUserId(userId);
        addEnterpriseInfo(tasks);
        PageInfo<Task> info = new PageInfo<Task>(tasks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);
    }

    /**
     * 添加企业信息
     *
     * @param tasks
     */
    private void addEnterpriseInfo(List<Task> tasks) {
        for (Task task : tasks) {
            User enterprise = userDao.findById(task.getEnterpriseId());
            if (enterprise == null) {
                logger.info("\naddEnterpriseInfo function enterprise javabean is null\n");
                continue;
            }
            logger.info("\naddEnterpriseInfo function enterprise ==> {}", enterprise.toString());
            task.setEnterpriseName(enterprise.getUsername());
            task.setEnterpriseHeadImg(enterprise.getHeadImage());
        }
        }

    }
