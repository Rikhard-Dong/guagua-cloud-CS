package com.guagua.web.common;

import com.guagua.bean.dto.ResultDto;
import com.guagua.service.common.UserService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-13 下午9:42
 * <p>
 * 维护用户个人信息
 */
@RestController
@RequestMapping("/user/profile")
public class UserProfileController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户的基本信息
     *
     * @param request request请求, 取得经过过滤器是保存的请求id
     * @return 结果
     */
    @GetMapping("/info/simple")
    public ResultDto getSimpleUserInfo(HttpServletRequest request) {

        return userService.getSimpleUserInfo(getUserId(request));
    }

    @GetMapping("/info")
    public ResultDto getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(getUserId(request));

    }

}
