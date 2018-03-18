package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.common.MessageDTO;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.MessageDao;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.service.BaseService;
import com.guagua.service.common.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ride
 * @date 18-3-18 下午3:27
 */
@Service("messageService")
public class MessageServiceImpl extends BaseService implements MessageService {

    private final UserDao userDao;
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, UserDao userDao) {
        this.messageDao = messageDao;
        this.userDao = userDao;
    }


    // 获取用户最近的五条消息
    public ResultDTO getLatestFive(Integer userId) {
        List<Message> messages = messageDao.findByUserIdLIMIT5(userId);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("messages", convert2DTO(messages));
    }

    /**
     * 将查询到bean转换为dto
     *
     * @param messages bean list
     * @return dto list
     */
    private List<MessageDTO> convert2DTO(List<Message> messages) {
        if (messages == null || messages.size() == 0) {
            return null;
        }
        List<MessageDTO> results = new ArrayList<MessageDTO>();

        for (Message message : messages) {
            MessageDTO dto = new MessageDTO(message);
            User sender = userDao.findById(message.getSenderId());
            User receiver = userDao.findById(message.getReceiverId());
            if (sender != null) {
                dto.setSenderUsername(sender.getUsername());
            }
            if (receiver != null) {
                dto.setReceiverUsername(receiver.getUsername());
            }
            results.add(dto);
        }

        return results;
    }
}
