package models;

import java.sql.Timestamp;

public class Moderator {

    // ################### Properties #####################
    private Integer moderatorId;
    private Topic topic;
    private User user;
    private Timestamp joinedOn;
    private Status status;
    
    // ################### Constructors #####################
    public Moderator(){
        
    }
    
    // ################### Other Methods #####################
    
    // ################### Getters-Setters #####################
    public void setModeratorId(Integer moderatorId){
        this.moderatorId = moderatorId;
    }
    public Integer getModeratorId(){
        return moderatorId;
    }

    public void setTopic(Topic topic){
        this.topic = topic;
    }
    public Topic getTopic(){
        return topic;
    }

    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return user;
    }
    public void setJoinedOn(Timestamp joinedOn){
        this.joinedOn = joinedOn;
    }
    public Timestamp getJoinedOn(){
        return joinedOn;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }
}