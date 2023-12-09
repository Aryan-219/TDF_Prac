package models;

public class Reply {
    
    // ################# Properties ################### 
    private Integer replyId;
    private Question question;
    private Post post;
    
    // ################# Constructors ################### 

    // ################# Other Methods ################### 

    // ################# Getters-Setters ################### 
    public Integer getReplyId(){
        return replyId;
    }
    public void setReplyId(Integer replyId){
        this.replyId = replyId;
    }
    public Question getQuestion(){
        return question;
    }
    public void setQuestion(Question question){
        this.question = question;
    }
    public Post gePost(){
        return post;
    }
    public void setPost(Post post){
        this.post = post;
    }
}
