package com.guagua.web.admin;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.admin.HandleMemberAuthenticationService;
import com.guagua.service.member.MemberAuthenticationService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-17 下午12:56
 * <p>
 * <p>
 * 审核会员用户认证操作
 */
@RestController
@RequestMapping("/admin/auditing/member")
public class AuditingMemberController extends BaseController {

    private final MemberAuthenticationService authenticationService;

    private final HandleMemberAuthenticationService handleService;

    @Autowired
    public AuditingMemberController(MemberAuthenticationService authenticationService, HandleMemberAuthenticationService handleService) {
        this.authenticationService = authenticationService;
        this.handleService = handleService;
    }

    /**
     * 获取会员用户的申请认证, 所有, 包括审核中, 审核通过和不通过的
     *
     * @param page 页数
     * @param size 每页多少条数据
     * @return result
     */
    @GetMapping("/authentication/all")
    public ResultDTO getAllAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return authenticationService.getAllAuthentication(page, size);
    }

    /**
     * 获取所有未审核的用户认证
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/authentication/unaudited")
    public ResultDTO getAllUnauditedAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return authenticationService.getAllUnauditedAuthentication(page, size);
    }

    /**
     * 获取所有审核通过的
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/authentication/pass")
    public ResultDTO getAllPassAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return authenticationService.getAllPassAuthentication(page, size);
    }

    /**
     * 获取所有审核不通过的认证信息
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/authentication/fail_pass")
    public ResultDTO getAllFailPassAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return authenticationService.getAllFailPassAuthentication(page, size);
    }

    /**
     * 进行审核认证, 返回审核结果
     *
     * @param applyId 对应会员用户申请的认证
     * @param result  审核记过, true审核通过, false 审核失败
     * @param comment 批注
     * @return result
     */
    @PostMapping("/verify/{id}")
    public ResultDTO verifyAuthenticationInfo(@PathVariable("id") Integer applyId,
                                              @RequestParam("result") boolean result,
                                              @RequestParam("comment") String comment,
                                              HttpServletRequest request) {

        return handleService.handleAuthentication(getUserId(request), applyId, result, comment);
    }
}
