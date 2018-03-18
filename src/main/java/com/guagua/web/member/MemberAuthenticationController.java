package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.member.MemberAuthenticationService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-16 下午6:29
 * <p>
 * 申请认证
 */
@RestController
@RequestMapping("/member/authentication")
public class MemberAuthenticationController extends BaseController {

    private final MemberAuthenticationService authenticationService;

    @Autowired
    public MemberAuthenticationController(MemberAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    /**
     * 用户查询自己的认证信息
     *
     * @param request 请求
     * @return 请求结果
     */
    @GetMapping("/info")
    public ResultDTO getInfo(HttpServletRequest request) {
        return authenticationService.getInfo(getUserId(request));
    }


    /**
     * 提交认证请求
     *
     * @param realName       真实姓名
     * @param idNumber       身份证号
     * @param positiveBase64 证件正面照
     * @param negativeBase64 证件反面照
     * @param request        请求
     * @return 结果
     */
    @PostMapping("/apply")
    public ResultDTO apply(String realName, String idNumber,
                           String positiveBase64, String negativeBase64,
                           HttpServletRequest request) {
        return authenticationService.apply(getUserId(request),
                realName, idNumber, positiveBase64, negativeBase64);
    }

    /**
     * 重新提交认证请求
     *
     * @param realName       真实姓名
     * @param idNumber       身份证号
     * @param positiveBase64 证件正面照
     * @param negativeBase64 证件反面照
     * @param request        请求
     * @return 结果
     */
    @PutMapping("/reapply")
    public ResultDTO reapply(String realName, String idNumber,
                             String positiveBase64, String negativeBase64,
                             HttpServletRequest request) {

        return authenticationService.reapply(getUserId(request),
                realName, idNumber, positiveBase64, negativeBase64);
    }

}
