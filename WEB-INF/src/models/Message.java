package models;

import java.sql.Timestamp;

public class Message {

    // ################# Properties ##################
    private Integer messageId;
    private User fromUser;
    private User toUser;
    private String message;
    private Timestamp postedOn;

    // ################# Constructors ##################
    public Message() {
    }

    // ################# Other Methods ##################

    // ################# Getters-Setters ##################
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Timestamp postedOn) {
        this.postedOn = postedOn;
    }

}
