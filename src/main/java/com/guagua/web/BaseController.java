package com.guagua.web;


import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-12 下午2:09
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());



    protected Integer getUserId(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        logger.info("########## userId ====> {} ##############", userId);

        return userId;
    }

}
