package com.itheima.demo.controller;

import java.util.Date;

public class Message {
    private String message;
    private Date sendTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return new Date();
    }

}