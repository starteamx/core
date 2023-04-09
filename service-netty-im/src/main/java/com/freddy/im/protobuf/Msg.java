package com.freddy.im.protobuf;

public class Msg {
    private String body;// 消息体
    private Head head;// 消息头

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }
}
