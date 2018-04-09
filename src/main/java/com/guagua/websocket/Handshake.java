package com.guagua.websocket;

import com.guagua.bean.entity.common.CustomerInfo;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.CustomerInfoDao;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.singleton.MemberSingleton;
import com.guagua.utils.SpringContextUtils;
import com.guagua.websocket.entity.UserInfo;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-30 下午4:24
 * <p>
 * websocket握手请求的拦截器
 */
public class Handshake implements HandshakeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Handshake.class);


    MemberSingleton singleton = MemberSingleton.getInstance();
    private CustomerInfoDao customerInfoDao;


    // 处理握手之前, 继续握手返回true
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        customerInfoDao = (CustomerInfoDao) SpringContextUtils.getContext().getBean("customerInfoDao");

        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            // 获取用户的类型, 0 表示是客服,1表示是匿名对象(比如客户, 服务对象之类), 2表示是其他用户(企业和后台管理人员)
            UserInfo userInfo = new UserInfo();

            Integer userType = Integer.valueOf(request.getServletRequest().getParameter("userType"));
            String username = request.getServletRequest().getParameter("username");
            userInfo.setUserType(userType);
            userInfo.setUsername(username);
            if (!userType.equals(UserInfo.USER_TYPE_ANON)) {
                Integer userId = Integer.valueOf(request.getServletRequest().getParameter("userId"));
                logger.info("user type ======> {}\n" +
                        "user id ==========> {}\n\n", userType, userId);
                userInfo.setUserId(String.valueOf(userId));

            } else if (userType.equals(UserInfo.USER_TYPE_ANON)) {
                // 如果是匿名用户, 完善信息
                String userId = request.getServletRequest().getParameter("userId");

                userInfo.setUserId(userId);

                Integer taskId = Integer.valueOf(request.getServletRequest().getParameter("taskId"));
                Integer sex = Integer.valueOf(request.getServletRequest().getParameter("sex"));
                Integer age = Integer.valueOf(request.getServletRequest().getParameter("age"));
                String phone = request.getServletRequest().getParameter("phone");
                String email = request.getServletRequest().getParameter("email");

                // 客户与任务相绑定
                userInfo.setTaskId(taskId);

                // 插入客户信息
                CustomerInfo customer = new CustomerInfo();
                customer.setUuid(userId);
                customer.setName(username);
                customer.setTaskId(taskId);
                customer.setSex(sex);
                customer.setAge(age);
                customer.setPhone(phone);
                customer.setEmail(email);

                insertCustomerInfo(customer);

                // 任务累计加1
                Integer csId = Integer.valueOf(request.getServletRequest().getParameter("csId"));
                singleton.accessCustomer(csId);
            }

            HttpSession session = request.getServletRequest().getSession(true);
            if (session != null) {
                map.put("USERINFO", userInfo);
            } else {
                logger.info("HttpSession is null!!!!!!!!!!!!!!");
                return false;
            }

        }
        return true;
    }

    // 握手之后
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        logger.info("<<<<<<<<<<<<<<<<<after handshake>>>>>>>>>>>>>>>>>>>");
    }


    /**
     * 插入一条客户信息
     *
     * @param customer customer
     */
    private void insertCustomerInfo(CustomerInfo customer) {
        Integer var1 = customerInfoDao.insertOne(customer);
        if (var1 == 0) {
            logger.error("##############客户信息插入失败!##############");
        }
    }
}
