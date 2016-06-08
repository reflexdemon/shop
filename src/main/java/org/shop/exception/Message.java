package org.shop.exception;

/**
 * Created by vprasanna on 6/8/2016.
 */
public class Message {

    private String code;
    private String message;

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
