package com.example.itcapstone.burglarkitten;

/**
 * Created by Ky on 7/16/2016.
 */
public class ChatMessage {
    private String name;
    private String message;

    public ChatMessage() {
        // necessary for Firebase's deserializer
    }
    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
