package com.guagua.web.test;

import com.guagua.bean.dto.ResultDto;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.utils.MailUtils;
import com.guagua.utils.QiniuUtils;
import com.guagua.web.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping("/upload/img")
    public ResultDto uploadImg(String base64) {
        String key = "img/aaa.jpg";
        try {
            QiniuUtils.uploadByBase64(base64, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultDto(DataDictionary.QUERY_SUCCESS);
    }

    public static void main(String[] args) {
        new Thread(new MailUtils("157@163.com", "111", "this is test")).run();
    }
}
