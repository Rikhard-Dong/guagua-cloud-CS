package com.guagua.web.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.TokenService;
import com.guagua.web.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author ride
 * @date 18-3-13 下午5:29
 */
@RestController
@RequestMapping("/token")
public class TokenController extends BaseController {


    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    /**
     * 请求刷新token
     *
     * @return 结果
     */
    @PostMapping("/refresh")
    public ResultDTO refreshJWT(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        logger.info("#####请求user id ===> {} ####", userId);

        return tokenService.refresh(userId);
    }
}
