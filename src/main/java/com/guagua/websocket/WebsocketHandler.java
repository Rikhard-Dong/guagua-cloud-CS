package com.guagua.websocket;

import com.guagua.dao.common.TaskEmploymentDao;
import com.guagua.singleton.MemberSingleton;
import com.guagua.utils.JacksonUtils;
import com.guagua.utils.SpringContextUtils;
import com.guagua.websocket.entity.Message;
import com.guagua.websocket.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ride
 * @date 18-3-30 下午6:12
 */
public class WebsocketHandler implements WebSocketHandler {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    // 所有站内用户, 包括匿名用户
    private List<WebSocketSession> users = new ArrayList<WebSocketSession>();

    private MemberSingleton memberSingleton = MemberSingleton.getInstance();


    private TaskEmploymentDao taskEmploymentDao
            = (TaskEmploymentDao) SpringContextUtils.getContext().getBean("taskEmploymentDao");

    // 连接成功后执行
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UserInfo userInfo = (UserInfo) session.getAttributes().get("USERINFO");

        logger.info("user info ===> {}", userInfo.toString());
        users.add(session);

        // 如果客服, 则将用户在线信息保存下来
        if (userInfo.getUserType().equals(UserInfo.USER_TYPE_MEMBER)) {
            Integer memberId = Integer.valueOf(userInfo.getUserId());
            memberSingleton.addOnlineMember(memberId);
            List<Integer> taskIds = taskEmploymentDao.getTaskIdsByMemberId(memberId);
            if (taskIds != null && taskIds.size() > 0) {
                for (Integer taskId : taskIds) {
                    memberSingleton.addMemberTask(taskId, memberId);
                }
            }
        }
    }

    // 处理信息
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage)
            throws Exception {
        String msgJson = webSocketMessage.getPayload().toString();
        logger.info("websocket message json ====> {}", msgJson);

        Message message = JacksonUtils.readValue(msgJson, Message.class);
        sendMessageToUser(message);
    }

    // 连接异常
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        UserInfo userInfo = (UserInfo) session.getAttributes().get("USERINFO");
        if (session.isOpen()) {
            session.close();
        }
        logger.info("连接出错, 关闭连接....\n message ===> {}", throwable.getMessage());
        users.remove(session);
        removeAbout(Integer.valueOf(userInfo.getUserId()));

    }

    // 关闭连接
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        UserInfo userInfo = (UserInfo) webSocketSession.getAttributes().get("USERINFO");

        logger.info("连接关闭, 关闭状态===>", closeStatus.getCode() + ":" + closeStatus.getReason());
        users.remove(webSocketSession);

        // 移除单例中该用户和相关的任务管理
        removeAbout(Integer.valueOf(userInfo.getUserId()));
    }

    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 消息组转发
     *
     * @param message 转发消息
     */
    private void sendMessageToUser(Message message) throws IOException {
        if (message == null) {
            return;
        }

        String messageJson = JacksonUtils.toJSon(message);
        if (messageJson == null) {
            return;
        }

        if (message.getType() == 0) {   // 站内聊天的情况
            // TODO 需要完成离线消息的功能, 保存用户的聊天记录的, 可以的话可以实现redis缓存的功能
            boolean flag = false;
            for (WebSocketSession user : users) {
                UserInfo userInfo = (UserInfo) user.getAttributes().get("USERINFO");
                if (userInfo.getUserType().equals(UserInfo.USER_TYPE_ANON)) {
                    continue;
                }
                if (StringUtils.equals(userInfo.getUserId(), message.getReceiverId())) {
                    // 转发消息
                    user.sendMessage(new TextMessage(messageJson));
                    flag = true;
                }
            }
            // 用户离线的情况
            if (!flag) {

            }

        } else if (message.getType() == 1) {    // 客服与客户交流的情况
            for (WebSocketSession user : users) {
                UserInfo userInfo = (UserInfo) user.getAttributes().get("USERINFO");
                if (userInfo == null || userInfo.getUserType().equals(UserInfo.USER_TYPE_OTHER)) {
                    continue;
                }
                if (StringUtils.equals(userInfo.getUserId(), message.getReceiverId())) {
                    user.sendMessage(new TextMessage(messageJson));
                }
            }

            // TODO 同样需要记录客户的聊天的内容

        }
    }

    /**
     * 移除单例中该客服的相关信息
     *
     * @param memberId memberId
     */
    private void removeAbout(Integer memberId) {
        memberSingleton.removeOnlineMember(memberId);
        List<Integer> taskIds = taskEmploymentDao.getTaskIdsByMemberId(memberId);
        if (taskIds != null && taskIds.size() > 0) {
            for (Integer taskId : taskIds) {
                memberSingleton.removeTask(taskId);
            }
        }
    }
}
