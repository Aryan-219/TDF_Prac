package models;

import java.sql.Timestamp;

public class Post {

    // ####################### Properties ####################
    private Integer postId;
    private User user;
    private Timestamp postedOn;
    private String post;
    private Integer likes;
    private Integer dislikes;
    private Integer spams;
    private Integer shares;
    private Boolean postType;
    private Status status;

    // ###################### Constructors ####################
    public Post(){

    }

    // ##################### Other Methods #################### 

    // #################### Getters-Setters ################### 
    public  Integer getPostId(){
        return postId;
    }
    public void setPostId(Integer postId){
        this.postId = postId;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Timestamp getPostedOn(){
        return postedOn;
    }
    public void setPostedOn(Timestamp postedOn){
        this.postedOn = postedOn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getSpams() {
        return spams;
    }

    public void setSpams(Integer spams) {
        this.spams = spams;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Boolean getPostType() {
        return postType;
    }

    public void setPostType(Boolean postType) {
        this.postType = postType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
