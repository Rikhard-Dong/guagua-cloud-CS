package com.guagua.web.common;

import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.entity.common.User;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.common.UserService;
import com.guagua.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ride
 * @date 18-3-12 下午2:35
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 判断手机号是否已经注册了
     *
     * @param phone 用户手机号
     * @return 结果
     */
    @GetMapping("/exists/{phone}")
    public ResultDto isPhoneExists(@PathVariable("phone") String phone) {
        return userService.isPhoneExists(phone);
    }

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return 结果
     */
    @PostMapping("/code")
    public ResultDto sendCode(String phone) {
        logger.info("phone ====> {}", phone);

        return userService.sendSMSCode(phone);
    }


    /**
     * 注册表单
     *
     * @param phone     手机号
     * @param code      验证码
     * @param password  密码
     * @param password2 确认密码
     * @param type      账号类型
     * @return 结果
     */
    @PostMapping("/register")
    public ResultDto register(String phone, String code, String password,
                              String password2, Integer type) {
        logger.info("\nphone ===> {}\ncode ===> {}\npassword ===> {}\npassword2 ===> {}\ntype ===> {}",
                phone, code, password, password2, type);

        if (!StringUtils.equals(password, password2)) {
            throw new CustomException(DataDictionary.USER_REGISTER_PASSWORD_NOT_MATCH);
        }

        User user = new User(phone, password, type);

        return userService.register(user, code);

    }
}
