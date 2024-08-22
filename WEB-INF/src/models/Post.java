package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;

import java.sql.Statement;

public class Post {
    public static final boolean QUESTION = true;
    public static final boolean REPLY = false;

    public static ServletContext appContext;
    public static String conURL;

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

    public Post() {
    }

    public Post(Integer postId, User user, Timestamp postedOn, String post, Integer likes, Integer dislikes,
            Integer spams, Integer shares, Boolean postType, Status status) {
        this.postId = postId;
        this.user = user;
        this.postedOn = postedOn;
        this.post = post;
        this.likes = likes;
        this.dislikes = dislikes;
        this.spams = spams;
        this.shares = shares;
        this.postType = postType;
        this.status = status;
    }

    public Post(User user, Timestamp postedOn, String post, Boolean postType) {
        this.user = user;
        this.postedOn = postedOn;
        this.post = post;
        this.postType = postType;
    }

    public boolean savePost() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "insert into posts (user_id, posted_on, post, post_type) value (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, user.getUserId());
            ps.setTimestamp(2, postedOn);
            ps.setString(3, post);
            ps.setBoolean(4, postType);

            int val = ps.executeUpdate();

            if (val == 1) {
                flag = true;
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    postId = rs.getInt(1);
                }
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Timestamp postedOn) {
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
