package com.guagua.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author ride
 * @date 18-3-30 下午6:13
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebsocketHandler(), "/chat")
                .setAllowedOrigins("*")
                .addInterceptors(new Handshake());

        registry.addHandler(new WebsocketHandler(), "/sockjs/chat")
                .addInterceptors(new Handshake()).withSockJS();
    }
}
