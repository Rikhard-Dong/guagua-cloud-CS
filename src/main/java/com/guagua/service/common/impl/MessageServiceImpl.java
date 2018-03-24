package com.guagua.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.common.MessageDTO;
import com.guagua.bean.entity.common.Message;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.MessageDao;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        List<Message> messages = messageDao.findByUserIdLIMIT5(user.getId());
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("messages",
                convert2DTO(messages, user.getUsername()));
    }

    // 得到用户的所有消息
    public ResultDTO getAllMessage(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        PageHelper.startPage(page, size);
        List<Message> messages = messageDao.findAllByUserId(user.getId());
        List<MessageDTO> dtos = convert2DTO(messages, user.getUsername());
        PageInfo<MessageDTO> info = new PageInfo<MessageDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("messages", info);
    }

    // 获取所欲已读的消息记录
    public ResultDTO getReadMessages(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        PageHelper.startPage(page, size);
        List<Message> messages = messageDao.findByUserIdAndRead(user.getId());
        List<MessageDTO> dtos = convert2DTO(messages, user.getUsername());
        PageInfo<MessageDTO> info = new PageInfo<MessageDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("messages", info);
    }

    // 获取所有未读的消息记录
    public ResultDTO getUnreadMessages(Integer userId, Integer page, Integer size) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        PageHelper.startPage(page, size);
        List<Message> messages = messageDao.findByUserIdAndUnread(user.getId());
        List<MessageDTO> dtos = convert2DTO(messages, user.getUsername());
        PageInfo<MessageDTO> info = new PageInfo<MessageDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("messages", info);
    }

    public Map<String, Object> countUnreadMessage(Integer userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", messageDao.countUnreadMessageByUserId(user.getId()));
        return result;
    }

    /**
     * 将查询到bean转换为dto
     *
     * @param messages bean list
     * @return dto list
     */
    private List<MessageDTO> convert2DTO(List<Message> messages, String receiver) {
        if (messages == null || messages.size() == 0) {
            return null;
        }
        List<MessageDTO> results = new ArrayList<MessageDTO>();

        for (Message message : messages) {
            MessageDTO dto = new MessageDTO(message);
            User sender = userDao.findById(message.getSenderId());
            if (sender != null) {
                dto.setSenderUsername(sender.getUsername());
            }
            dto.setReceiverUsername(receiver);
            results.add(dto);
        }

        return results;
    }
}
