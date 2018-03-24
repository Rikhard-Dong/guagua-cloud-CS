package com.guagua.web.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.MessageService;
import com.guagua.web.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-18 下午3:25
 * <p>
 * 获取查看更新删除消息
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 得到该用户所有的消息通知
     *
     * @param request 请求
     * @return result
     */
    @GetMapping("/all")
    public ResultDTO getAllMessage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "30") Integer size,
                                   HttpServletRequest request) {
        return messageService.getAllMessage(getUserId(request), page, size);
    }

    /**
     * 得到最近的五条用户消息
     *
     * @param request request
     * @return result
     */
    @GetMapping("/latest")
    public ResultDTO getLatestMessage(HttpServletRequest request) {
        return messageService.getLatestFive(getUserId(request));
    }

    /**
     * 获取所有已读的消息记录
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/read")
    public ResultDTO getReadMessage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "30") Integer size,
                                    HttpServletRequest request) {
        return messageService.getReadMessages(getUserId(request), page, size);
    }

    /**
     * 查询所有未读的消息记录
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/unread")
    public ResultDTO getUnreadMessage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "30") Integer size,
                                      HttpServletRequest request) {
        return messageService.getUnreadMessages(getUserId(request), page, size);
    }

    /**
     * 统计用户未读消息的条数
     *
     * @param request
     * @return
     */
    @GetMapping("/unread/count")
    public Map<String, Object> getUnreadMessageCount(HttpServletRequest request) {
        return messageService.countUnreadMessage(getUserId(request));
    }
}
