package com.bookstore.backend.websocket;


import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    Integer code;

    String text;
}
