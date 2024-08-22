package models;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class Topic {
    // ########### Properties #################
    public static ServletContext appContext;
    public static String conURL;

    private Integer topicId;
    private String name;
    private Integer allPosts;
    private Integer openPosts;
    private Timestamp lastPost;
    private Integer activeUsers;

    // ########### Constructors ###############
    public Topic() {

    }

    public Topic(Integer topicId, String name, Integer allPosts, Integer openPosts, Timestamp lastPost,
            Integer activeUsers) {
        this.topicId = topicId;
        this.name = name;
        this.allPosts = allPosts;
        this.openPosts = openPosts;
        this.lastPost = lastPost;
        this.activeUsers = activeUsers;
    }

    public Topic(Integer topicId) {
        this.topicId = topicId;
    }

    public Topic(Integer topicId, String name) {
        this.topicId = topicId;
        this.name = name;
    }

    // ########### Other Methods ##############
    public static ArrayList<Topic> collectAllTopics() {
        ArrayList<Topic> topics = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(conURL);) {
            String query = "select * from topics";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                topics.add(new Topic(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
                        rs.getInt(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    // ########### Getter-Setters ##############
    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(Integer allPosts) {
        this.allPosts = allPosts;
    }

    public Integer getOpenPosts() {
        return openPosts;
    }

    public void setOpenPosts(Integer openPosts) {
        this.openPosts = openPosts;
    }

    public Integer getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Timestamp getLastPost() {
        return lastPost;
    }

    public void setLastPost(Timestamp lastPost) {
        this.lastPost = lastPost;
    }

}