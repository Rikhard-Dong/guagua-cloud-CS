package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.Message;
import com.guagua.utils.DateUtils;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-18 下午12:44
 * <p>
 * 消息dto类
 */
public class MessageDTO {

    private Integer id;             // id
    private Integer senderId;       // 发送者id
    private String senderUsername;  // 发送者用户名
    private Integer receiverId;     // 接受者id
    private String receiverUsername;    // 接受者用户名
    private String title;           // 消息标题
    private String content;         // 消息内容
    private String sendTime;        // 发送时间
    private Boolean isRead;         // 是否已读 false
    private String status;          // 是否已读

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.title = message.getTitle();
        this.content = message.getContent();
        this.sendTime = DateUtils.date2StrCN(message.getSendTime());
        this.isRead = message.getIsRead();
        if (this.isRead) {
            this.status = "已读";
        } else {
            this.status = "未读";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", senderUsername='" + senderUsername + '\'' +
                ", receiverId=" + receiverId +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", isRead=" + isRead +
                ", status='" + status + '\'' +
                '}';
    }
}
