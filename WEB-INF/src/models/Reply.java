package models;

import javax.servlet.ServletContext;

public class Reply {
    public static ServletContext appContext;
    public static String conURL;

    private Integer replyId;
    private Question question;
    private Post post;

    public Reply() {
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
