package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Task;
import com.guagua.service.enterprise.ReleaseTaskService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /*  *******************************
     *  任务投标方面操作
     *
     ***********************************/

    /**
     * 查看所有申请记录
     *
     * @param taskId
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/{taskId}/applications")
    public ResultDTO getAllTaskApplicationWithTaskId(@PathVariable("taskId") Integer taskId,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "30") Integer size,
                                                     HttpServletRequest request) {
        return taskService.queryTaskApplicationWithTaskId(getUserId(request), taskId, page, size);
    }

    /**
     * 得到所有未处理的申请记录
     *
     * @param taskId
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/{taskId}/applications/untreated")
    public ResultDTO getAllUntreatedTaskApplication(@PathVariable("taskId") Integer taskId,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "30") Integer size,
                                                    HttpServletRequest request) {
        return taskService.queryAllUntreatedApplication(getUserId(request), taskId, page, size);
    }

    /**
     * 同意申请
     *
     * @param taskId
     * @param applicationId
     * @param request
     * @return
     */
    @PutMapping("/{taskId}/application/agree/{applicationId}")
    public ResultDTO agreeApplication(@PathVariable("taskId") Integer taskId,
                                      @PathVariable("applicationId") Integer applicationId,
                                      HttpServletRequest request) {

        return taskService.agreeApplication(getUserId(request), taskId, applicationId);
    }

    /**
     * 拒绝申请
     *
     * @param taskId
     * @param applicationId
     * @param request
     * @return
     */
    @PutMapping("/{taskId}/application/refuse/{applicationId}")
    public ResultDTO refuseApplication(@PathVariable("taskId") Integer taskId,
                                       @PathVariable("applicationId") Integer applicationId,
                                       HttpServletRequest request) {
        return taskService.refuseApplication(getUserId(request), taskId, applicationId);
    }


    /**
     * 查询任务对应的客服
     *
     * @param taskId
     * @param request
     * @return
     */
    @GetMapping("/{taskId}/customer_service")
    public ResultDTO getCustomerService(@PathVariable("taskId") Integer taskId,
                                        HttpServletRequest request) {
        return taskService.getMyCustomerService(getUserId(request), taskId);
    }


    /**
     * 查询所有建立联系的客服
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/all/customer_service")
    public ResultDTO getAllCustomerService(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "30") Integer size,
                                           HttpServletRequest request) {
        return taskService.getAllCustomerService(getUserId(request), page, size);
    }

    /**
     * 将任务和知识库绑定
     *
     * @param taskId
     * @param knowledgeId
     * @param request
     * @return
     */
    @PostMapping("/{taskId}/bind/knowledge/{knowledgeId}")
    public ResultDTO bindKnowledge(@PathVariable("taskId") Integer taskId,
                                   @PathVariable("knowledgeId") Integer knowledgeId,
                                   HttpServletRequest request) {
        return taskService.bindKnowledge(getUserId(request), taskId, knowledgeId);
    }

    /**
     * 批量绑定任务和知识库
     *
     * @param taskId
     * @param knowledgeIds
     * @param request
     * @return
     */
    @PostMapping("/{taskId}/bind/knowledge/batch/{knowledgeIds}")
    public ResultDTO bindKnowledge(@PathVariable("taskId") Integer taskId,
                                   @PathVariable("knowledgeIds") String knowledgeIds,
                                   HttpServletRequest request) {

        return taskService.bindKnowledgeBatch(getUserId(request), taskId, knowledgeIds);
    }

    /**
     * 获取该任务的顾客接入地址
     *
     * @param taskId
     * @param request
     * @return
     */
    @GetMapping("/{taskId}/customer_access_url")
    public ResultDTO getCustomerAccessUrl(@PathVariable("taskId") Integer taskId,
                                          HttpServletRequest request) {

        return taskService.getCustomerAccessUrl(taskId);
    }
}
