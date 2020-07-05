package com.example.postboxoriginal;

public class Messagesclass {
    private String sender;
    private String receiver;
    private String message;
    private String timestamp;
    private String status;
    private String messageid;

    public Messagesclass() {
        this.sender = null;
        this.receiver = null;
        this.message = null;
        this.timestamp = null;
        this.status=null;
        this.messageid=null;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
