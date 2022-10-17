package com.bookstore.backend.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/api/order/{user-id}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
@Slf4j
public class WebSocketServer {

    private static final ConcurrentHashMap<Long, Session> SESSIONS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("user-id") Long userId) {
        if (userId == null) return;
        SESSIONS.put(userId, session);
        log.info("新用户" + userId + "已连接");
    }

    @OnMessage
    public void onMessage(Message message) {
        log.info("websocket收到新消息：" + message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("user-id") Long userId) {
        SESSIONS.remove(userId);
        log.info("用户" + userId + "关闭连接");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("websocket发生错误");
        throwable.printStackTrace();
    }

    public void send(Long userId, Message message) {
        Session session = SESSIONS.get(userId);
        if (session == null) {
            System.out.println("session已取消链接");
            return;
        }

        try {
            session.getBasicRemote().sendObject(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
