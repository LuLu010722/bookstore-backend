package com.bookstore.backend.listener;

import com.bookstore.backend.service.OrderService;
import com.bookstore.backend.websocket.Message;
import com.bookstore.backend.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class OrderListener {
    @Resource
    private OrderService orderService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private WebSocketServer webSocketServer;

    @KafkaListener(topics = "topic1", groupId = "group_topic_test")
    public void topic1Listener(ConsumerRecord<String, String> record) {
        int index = record.value().indexOf(']');
        String first = record.value().substring(1, index);
        String second = record.value().substring(index + 1);

        List<Long> bookIds = Arrays.stream(first.split(", ")).map(Long::valueOf).toList();
        Long userId = Long.valueOf(second);
        try {
            orderService.createOrder(bookIds, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Message message = new Message();
        message.setCode(200);
        message.setText("订单创建完成");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webSocketServer.send(userId, message);
    }

}
