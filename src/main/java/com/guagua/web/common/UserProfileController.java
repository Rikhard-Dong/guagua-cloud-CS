package com.guagua.web.common;

import com.guagua.bean.dto.ResultDto;
import com.guagua.service.common.UserService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 获取用户的简单信息
     *
     * @param request request请求, 取得经过过滤器是保存的请求id
     * @return 结果
     */
    @GetMapping("/info/simple")
    public ResultDto getSimpleUserInfo(HttpServletRequest request) {

        return userService.getSimpleUserInfo(getUserId(request));
    }

    /**
     * 获取用户的基本信息
     *
     * @param request request请求
     * @return 结果
     */
    @GetMapping("/info")
    public ResultDto getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(getUserId(request));

    }

    /**
     * 更新用户头像
     *
     * @param request   request
     * @param imgBase64 图片地址
     * @return 结果
     */
    @PutMapping("/head")
    public ResultDto updateHeadImage(HttpServletRequest request, String imgBase64) {

        return userService.updateHeadImage(getUserId(request), imgBase64);
    }

    /**
     * 在用户已知密码情况下, 更新密码
     *
     * @param request     request请求
     * @param oldPassword 旧密码
     * @param password    新密码
     * @param repassword  确认密码
     * @return 结果
     */
    @PutMapping("/password")
    public ResultDto updatePassword(
            HttpServletRequest request,
            String oldPassword,
            String password,
            String repassword) {

        logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                "old password ==> {} password ===> {} repassword ===> {}\n\n\n", oldPassword, password, repassword);

        return userService.updatePassword(getUserId(request), oldPassword, password, repassword);
    }

    /**
     * 更新用户名
     *
     * @param request  request
     * @param username 更新用户名
     * @return 结果
     */
    @PutMapping("/username")
    public ResultDto updateUsername(HttpServletRequest request, String username) {

        return userService.updateUsername(getUserId(request), username);
    }

    /**
     * 更新用户性别
     *
     * @param request request
     * @param sex     性别
     * @return result
     */
    @PutMapping("/sex")
    public ResultDto updateSex(HttpServletRequest request, Integer sex) {

        return userService.updateSex(getUserId(request), sex);
    }

    /**
     * 更新qq
     *
     * @param request request
     * @param qq      qq
     * @return result
     */
    @PutMapping("/qq")
    public ResultDto updateQQ(HttpServletRequest request, String qq) {

        return userService.updateQQ(getUserId(request), qq);
    }

    /**
     * 更新微信
     *
     * @param request request
     * @param wechat  wechat
     * @return result
     */
    @PutMapping("/wechat")
    public ResultDto updateWechat(HttpServletRequest request, String wechat) {

        return userService.updateWechat(getUserId(request), wechat);
    }

    /**
     * 更新用户描述
     *
     * @param request     request
     * @param description 用户描述
     * @return result
     */
    @PutMapping("/description")
    public ResultDto updateDescription(HttpServletRequest request, String description) {

        return userService.updateDescription(getUserId(request), description);
    }

    /**
     * 更新用户教育经历
     *
     * @param request     request
     * @param educational 教育经历
     * @return result
     */
    @PutMapping("/educational")
    public ResultDto updateEducational(HttpServletRequest request, String educational) {

        return userService.updateEducational(getUserId(request), educational);
    }

    @PutMapping("/address")
    public ResultDto updateAddress(HttpServletRequest request, String address) {
        return userService.updateAddress(getUserId(request), address);
    }

}
