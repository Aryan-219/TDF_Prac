package models;

public class Question {

    // ####################### Properties #################### 
    private Integer questionId;
    private Topic topic;
    private String title;
    private Post post;
    private Integer replies;

    // ####################### Constructors #################### 
    public Question(){

    }

    // ####################### Other Methods ###################

    // ####################### Getters-Setters #################
    public Integer getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public Integer getReplies() {
        return replies;
    }
    public void setReplies(Integer replies) {
        this.replies = replies;
    }
}
