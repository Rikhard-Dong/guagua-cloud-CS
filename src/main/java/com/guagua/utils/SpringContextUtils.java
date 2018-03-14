package com.guagua.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ride
 * @date 18-3-14 下午10:24
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
