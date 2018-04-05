package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.member.MemberTaskService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-4-2 下午8:26
 */
@RestController
@RequestMapping("/member/task")
public class MemberTaskController extends BaseController {

    private final MemberTaskService memberTaskService;

    @Autowired
    public MemberTaskController(MemberTaskService memberTaskService) {
        this.memberTaskService = memberTaskService;
    }

    /**
     * 获取用户参与的所有任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/participate/all")
    public ResultDTO getParticipateAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "30") Integer size,
                                       HttpServletRequest request) {
        return memberTaskService.getParticipateAll(getUserId(request), page, size);

    }

    /**
     * 获取用户参与的未开始的任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/participate/not_start")
    public ResultDTO getNotStart(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "30") Integer size,
                                 HttpServletRequest request) {
        return memberTaskService.getParticipateNotStart(getUserId(request), page, size);
    }


    /**
     * 获取用户参与的进行中的任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/participate/have_in_hand")
    public ResultDTO getStartHaveInHand(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "30") Integer size,
                                        HttpServletRequest request) {
        return memberTaskService.getParticipateHaveInHand(getUserId(request), page, size);
    }

    /**
     * 查询所有结束的任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/participate/end")
    public ResultDTO getParticipateEnd(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "30") Integer size,
                                       HttpServletRequest request) {

        return memberTaskService.getParticipateEnd(getUserId(request), page, size);
    }

    /**
     * 查询所有取消的任务
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/participate/cancel")
    public ResultDTO getParticipateCancel(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "30") Integer size,
                                          HttpServletRequest request) {
        return memberTaskService.getParticipateCancel(getUserId(request), page, size);
    }
}
