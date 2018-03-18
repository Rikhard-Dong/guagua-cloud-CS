package com.guagua.web.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.MailService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-15 下午8:52
 * <p>
 * 用户绑定邮箱, 管理员群发邮件之类功能
 */
@RestController
@RequestMapping("/email")
public class MailController extends BaseController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 发送邮件验证码
     *
     * @param request request
     * @param email   邮箱地址
     * @return result
     */
    @PostMapping("/code")
    public ResultDTO sendEmailCode(HttpServletRequest request, String email) {

        return mailService.sendEmailCode(getUserId(request), email);
    }


    @PutMapping("/bind")
    public ResultDTO bindEmail(HttpServletRequest request, String email, String code) {
        return mailService.updateMail(getUserId(request), email, code);
    }

}
