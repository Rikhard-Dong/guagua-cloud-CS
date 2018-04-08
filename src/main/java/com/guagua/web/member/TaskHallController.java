package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.question.Answer;
import com.guagua.service.member.MemberTaskService;
import com.guagua.web.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ride
 * @date 18-3-24 下午7:55
 * <p>
 * 任务大厅, 浏览任务, 参与任务竞标
 */
@RestController
@RequestMapping("/member/task_hall")
public class TaskHallController extends BaseController {

    private final MemberTaskService taskService;

    @Autowired
    public TaskHallController(MemberTaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 查询所有可参与的任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/all")
    public ResultDTO getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "30") Integer size,
                            HttpServletRequest request) {

        return taskService.listAll(getUserId(request), page, size);
    }

    /**
     * 查询单个任务的详情
     *
     * @param taskId
     * @param request
     * @return
     */
    @GetMapping("/query/detail/{taskId}")
    public ResultDTO queryDetail(@PathVariable("taskId") Integer taskId,
                                 HttpServletRequest request) {
        return taskService.queryDetail(getUserId(request), taskId);
    }

    /**
     * 申请任务
     *
     * @param taskId
     * @param request
     * @return
     */
    @PostMapping("/task/{taskId}/application")
    public ResultDTO applicationTask(@PathVariable("taskId") Integer taskId,
                                     HttpServletRequest request) {

        return taskService.applicationTask(getUserId(request), taskId);
    }

    /**
     * 得到试卷
     *
     * @param taskId
     * @param request
     * @return
     */
    @GetMapping("/task/{taskId}/get_examination")
    public ResultDTO getExamination(@PathVariable("taskId") Integer taskId,
                                    HttpServletRequest request) {

        return taskService.getExamination(getUserId(request), taskId);
    }

    /**
     * 提交答卷
     *
     * @param taskId
     * @param answers
     * @param request
     * @return
     */
    @PostMapping("/task/{taskId}/examination/{paperId}/hand_exam")
    public ResultDTO handExamination(@PathVariable("taskId") Integer taskId,
                                     @PathVariable("paperId") Integer paperId,
                                     @RequestBody List<Answer> answers,
                                     HttpServletRequest request) {

        return taskService.handExamination(getUserId(request), taskId, paperId, answers);
    }
}
