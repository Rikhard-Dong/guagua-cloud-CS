package com.guagua.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.question.ExaminationPaperDTO;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.TaskApplication;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.question.*;
import com.guagua.dao.common.*;
import com.guagua.dao.question.*;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.member.MemberTaskService;
import com.mysql.fabric.xmlrpc.base.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private final AnswerSheetDao sheetDao;

    private final ExaminationPaperDao paperDao;

    private final QuestionBankDao bankDao;

    private final QuestionExaminationPaperDao questionExaminationPaperDao;

    private final QuestionItemDao itemDao;

    private final RuleOfGenerationExaminationDao ruleDao;

    private final QuestionDao questionDao;

    private final AnswerDao answerDao;

    @Autowired
    public MemberTaskServiceImpl(UserDao userDao,
                                 MessageDao messageDao,
                                 TaskDao taskDao,
                                 TaskApplicationDao taskApplicationDao,
                                 TaskEmploymentDao taskEmploymentDao,
                                 AnswerSheetDao sheetDao,
                                 ExaminationPaperDao paperDao,
                                 QuestionBankDao bankDao,
                                 QuestionExaminationPaperDao questionExaminationPaperDao,
                                 QuestionItemDao itemDao,
                                 RuleOfGenerationExaminationDao ruleDao,
                                 QuestionDao questionDao,
                                 AnswerDao answerDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.taskDao = taskDao;
        this.taskApplicationDao = taskApplicationDao;
        this.taskEmploymentDao = taskEmploymentDao;
        this.sheetDao = sheetDao;
        this.paperDao = paperDao;
        this.bankDao = bankDao;
        this.questionExaminationPaperDao = questionExaminationPaperDao;
        this.itemDao = itemDao;
        this.ruleDao = ruleDao;
        this.questionDao = questionDao;
        this.answerDao = answerDao;
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

        // 需要进行笔试才行 查询客服在该任务下是否有进行过笔试
        if (task.getIsNeedExamination() == 1) {
            AnswerSheet sheet = sheetDao.findByAnswererAndTaskId(userId, taskId);
            if (sheet == null) {
                throw new CustomException(DataDictionary.NEED_EXAMINATION).addData("detail", "该任务需要进行笔试之后才能提交申请");
            }
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

    // 得到试卷
    @Transactional
    public ResultDTO getExamination(Integer userId, Integer taskId) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        if (task.getIsNeedExamination() == 0) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "该任务不需要笔试试卷");
        }
        ExaminationPaper paper = null;
        if (task.getExaminationType() == 0) {
            // 自动生成
            paper = autoCreateExamination(task.getRuleId(), userId, task.getTitle());
        } else if (task.getExaminationType() == 1) {
            // 手动创建
            paper = paperDao.findById(task.getExaminationId());
        }

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("paper", convert2ExaminationPaperDTO(paper));
    }

    // 提交试卷
    public ResultDTO handExamination(Integer userId, Integer taskId, Integer paperId, List<Answer> answers) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        if (task.getIsNeedExamination() == 0) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "当前试卷不需要笔试");
        }
        // 创建答卷
        AnswerSheet sheet = new AnswerSheet(userId, taskId, paperId);
        Integer var1 = sheetDao.insertOne(sheet);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL)
                    .addData("detail", "创建答卷失败");
        }

        // 创建答题, 在t_question_answer中建立question和sheet的联系
        for (Answer answer : answers) {
            answer.setSheetId(sheet.getId());
        }
        Integer var2 = answerDao.insertBatch(answers);
        if (var2 != answers.size()) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "创建答题失败");
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
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

    /**
     * 将paper 转换为dto
     *
     * @param paper
     * @return
     */
    private ExaminationPaperDTO convert2ExaminationPaperDTO(ExaminationPaper paper) {
        if (paper == null) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "获取试卷失败");
        }

        ExaminationPaperDTO result = new ExaminationPaperDTO(paper);
        // 创建者名字
        User creator = userDao.findById(paper.getCreator());
        if (creator != null) {
            result.setCreatorName(creator.getUsername());
        }
        // 所属题库名称
        QuestionBank bank = bankDao.findById(paper.getBankId());
        if (bank != null) {
            result.setBankName(bank.getBankName());
        }

        // 题目添加情况
        List<Question> singleQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 0);
        List<Question> multipleQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 1);
        List<Question> judgementQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 2);
        List<Question> textQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 3);

        result.setSingleCurNum(singleQuestions == null ? 0 : singleQuestions.size());
        if (singleQuestions != null && singleQuestions.size() > 0) {
            for (Question question : singleQuestions) {
                List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
                question.setStandardAnswer(null);
                question.setItems(items);
            }
        }
        result.setSingleQuestions(singleQuestions);

        result.setMultipleCurNum(multipleQuestions == null ? 0 : multipleQuestions.size());
        if (multipleQuestions != null && multipleQuestions.size() > 0) {
            for (Question question : multipleQuestions) {
                List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
                question.setItems(items);
                question.setStandardAnswer(null);
            }
        }
        result.setMultipleQuestions(multipleQuestions);

        result.setJudgementCurNum(judgementQuestions == null ? 0 : judgementQuestions.size());
        if (judgementQuestions != null && judgementQuestions.size() > 0) {
            List<QuestionItem> items = new ArrayList<QuestionItem>();
            items.add(itemDao.findById(1));
            items.add(itemDao.findById(0));
            for (Question question : judgementQuestions) {
                question.setItems(items);
                question.setStandardAnswer(null);
            }
        }
        result.setJudgementQuestions(judgementQuestions);

        result.setTextCurNum(textQuestions == null ? 0 : textQuestions.size());
        result.setTextQuestions(textQuestions);

        return result;
    }

    /**
     * 根据规则自动创建一份规则
     *
     * @param ruleId
     * @return
     */
    private ExaminationPaper autoCreateExamination(Integer ruleId, Integer userId, String taskTitle) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        RuleOfGenerationExamination rule = ruleDao.findById(ruleId);
        if (rule == null) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "获取试卷失败, 生成试卷的规则不存在");
        }

        ExaminationPaper paper = new ExaminationPaper();
        paper.setName(user.getUsername() + "关于任务" + taskTitle + "笔试题目");
        paper.setDescription("根据自动生成的规则创建的试卷");
        paper.setNum(rule);

        Integer var1 = paperDao.insertOne(paper);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL)
                    .addData("detail", "自动生成试卷失败");
        }
        // 将题库里的题目与试卷相绑定
        List<Question> singleQuestions = bankDao.findByType(0);
        List<Question> multipleQuestions = bankDao.findByType(1);
        List<Question> judgementQuestions = bankDao.findByType(2);
        List<Question> textQuestions = bankDao.findByType(3);


        List<Question> randomQuestions;

        if (singleQuestions != null && singleQuestions.size() >= paper.getSingleNum()) {
            randomQuestions = getRandomQuestions(singleQuestions, paper.getSingleNum());
            createPaperAndQuestionContact(paper, randomQuestions);
        } else {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "题库中单选题数量不满足要求");
        }

        if (multipleQuestions != null && multipleQuestions.size() >= paper.getMultipleNum()) {
            randomQuestions = getRandomQuestions(multipleQuestions, paper.getMultipleNum());
            createPaperAndQuestionContact(paper, randomQuestions);
        } else {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "题库中多选题数量不满足要求");
        }

        if (judgementQuestions != null && judgementQuestions.size() >= paper.getJudgementNum()) {
            randomQuestions = getRandomQuestions(judgementQuestions, paper.getJudgementNum());
            createPaperAndQuestionContact(paper, randomQuestions);
        } else {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "题库中判断题数量不满足要求");
        }

        if (textQuestions != null && textQuestions.size() >= paper.getTextNum()) {
            randomQuestions = getRandomQuestions(textQuestions, paper.getTextNum());
            createPaperAndQuestionContact(paper, randomQuestions);
        } else {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "题库中文字题数量不满足要求");
        }

        return paper;
    }


    /**
     * 从题库中随机获取到题目
     *
     * @param questions
     * @param num
     * @return
     */
    private List<Question> getRandomQuestions(List<Question> questions, Integer num) {
        if (num == 0) {
            return null;
        }
        Random random = new Random();
        List<Integer> indexTempList = new ArrayList<Integer>();
        List<Question> result = new ArrayList<Question>();
        Integer temp;
        for (Integer i = 0; i < num; i++) {
            temp = random.nextInt(questions.size());
            if (!indexTempList.contains(temp)) {
                indexTempList.add(temp);
                result.add(questions.get(temp));
            } else {
                i--;
            }
        }

        return result;
    }

    /**
     * 创建试卷和问题的联系
     *
     * @param paper
     * @param randomQuestions
     */
    private void createPaperAndQuestionContact(ExaminationPaper paper, List<Question> randomQuestions) {
        if (randomQuestions != null && randomQuestions.size() > 0) {
            for (Question question : randomQuestions) {
                Integer var2 = questionExaminationPaperDao
                        .insertOne(new QuestionExaminationPaper(paper.getId(), question.getId()));
                if (var2 == 0) {
                    throw new CustomException(DataDictionary.INSERT_FAIL)
                            .addData("detail", "建立试卷和问题联系时插入失败");
                }
            }
        }
    }
}
