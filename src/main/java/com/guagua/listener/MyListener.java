package com.guagua.listener;

import com.guagua.service.common.TimerTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ride
 * @date 18-3-12 下午6:19
 * <p>
 * 监听器, 启动一些任务
 */
public class MyListener implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TimerTaskService timerTaskService;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        timerTaskService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext())
                .getBean(TimerTaskService.class);

        Timer timer = new Timer();
        // 定时器一秒钟后启动, 然后每五分钟执行一次清空手机过期验证码, 每十五分钟清楚一次邮箱验证码
        timer.schedule(new ClearPhoneValidateCodeTimerTask(), 1000, 5 * 60 * 1000);
        timer.schedule(new ClearEmailValidateCodeTimerTask(), 1000, 15 * 60 * 1000);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * 定时清除t_phone_validate_code中的无效数据
     */
    class ClearPhoneValidateCodeTimerTask extends TimerTask {


        public void run() {
            timerTaskService.clearTablePhoneValidateCodeData();
        }
    }

    /**
     * 定时清除t_email_validate_code中的无效数据
     */
    class ClearEmailValidateCodeTimerTask extends TimerTask {

        public void run() {
            timerTaskService.clearTableMailValidateCodeData();
        }
    }


}
