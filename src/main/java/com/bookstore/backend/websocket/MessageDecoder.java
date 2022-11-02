package com.bookstore.backend.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

@Slf4j
public class MessageDecoder implements Decoder.Text<Message> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Message decode(String s) throws DecodeException {
        Message message;
        try {
            message = mapper.readValue(s, Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info(message.toString());
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
