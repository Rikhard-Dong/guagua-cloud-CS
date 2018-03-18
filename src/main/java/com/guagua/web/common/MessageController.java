package com.guagua.web.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.MessageService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResultDTO getAllMessage(HttpServletRequest request) {
        // TODO 具体分页情况待定
        return null;
    }

    /**
     * 得到最近的五条用户消息
     *
     * @param request request
     * @return result
     */
    @GetMapping("/latest")
    public ResultDTO getLatedMessage(HttpServletRequest request) {
        return messageService.getLatestFive(getUserId(request));
    }
}
