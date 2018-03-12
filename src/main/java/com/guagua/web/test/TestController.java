package com.guagua.web.test;

import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.web.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @RequestMapping("/page")
    public String test() {

        return "this is test page, 这是测试页面";
    }

    @RequestMapping("/exception/{flag}")
    public String testException(@PathVariable boolean flag) throws CustomException {

        logger.info("flag ===> {}", flag);

        if (flag) {
            throw new CustomException(DataDictionary.LOGIN_SUCCESS);
        }
        return "this is exception test page, 测试";

    }
}
