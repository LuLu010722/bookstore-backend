package com.bookstore.backend.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

@Slf4j
public class MessageEncoder implements Encoder.Text<Message> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Message message) throws EncodeException {
        String value;
        try {
            value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info(value);
        return value;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
