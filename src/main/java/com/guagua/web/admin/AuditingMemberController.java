package com.guagua.web.admin;

import com.guagua.bean.dto.ResultDto;
import com.guagua.service.member.MemberAuthenticationService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public AuditingMemberController(MemberAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * 获取会员用户的申请认证, 所有, 包括审核中, 审核通过和不通过的
     *
     * @param page 页数
     * @param size 每页多少条数据
     * @return result
     */
    @GetMapping("/authentication/all")
    public ResultDto getAllAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
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
    public ResultDto getAllUnauditedAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
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
    public ResultDto getAllPassAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
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
    public ResultDto getAllFailPassAuthentication(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return authenticationService.getAllFailPassAuthentication(page, size);
    }
}
