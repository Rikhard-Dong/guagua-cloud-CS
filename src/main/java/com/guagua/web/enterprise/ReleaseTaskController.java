package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Task;
import com.guagua.service.enterprise.ReleaseTaskService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-23 下午7:57
 * <p>
 * 发布任务
 */
@RestController
@RequestMapping("/enterprise/task")
public class ReleaseTaskController extends BaseController {

    private final ReleaseTaskService taskService;

    @Autowired
    public ReleaseTaskController(ReleaseTaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResultDTO createTask(Task task, HttpServletRequest request) {

        logger.info("task ====> {}", task);

        return taskService.createTask(getUserId(request), task);
    }

    /**
     * 查询发布者的所有任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/query")
    public ResultDTO queryAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              HttpServletRequest request) {
        return taskService.queryTask(getUserId(request), page, size);
    }

    /**
     * 根据任务状态查询所有任务
     *
     * @param status
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/query/status/{status}")
    public ResultDTO queryByStatus(@PathVariable("status") Integer status,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "30") Integer size,
                                   HttpServletRequest request) {

        return taskService.queryTaskByStatus(getUserId(request), status, page, size);
    }

    /**
     * 查询单个任务的详情
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/query/detail/{id}")
    public ResultDTO queryDetail(@PathVariable("id") Integer id,
                                 HttpServletRequest request) {

        return taskService.queryDetail(getUserId(request), id);
    }

    /**
     * 取消任务
     *
     * @param taskId
     * @param request
     * @return
     */
    @PutMapping("/cancel/{taskId}")
    public ResultDTO cancelTask(@PathVariable("taskId") Integer taskId,
                                HttpServletRequest request) {
        return taskService.cancelTask(getUserId(request), taskId);
    }
}
