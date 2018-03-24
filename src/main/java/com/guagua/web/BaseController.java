package com.guagua.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ride
 * @date 18-3-12 下午2:09
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 进行日期时间类的从字符串到相应的类型转换
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        binder.registerCustomEditor(Time.class,
                new CustomDateEditor(new SimpleDateFormat("HH:mm:ss"), true));
    }

    protected Integer getUserId(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        logger.info("########## userId ====> {} ##############", userId);

        return userId;
    }

}
