package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Topic {

    // ####################### Properties #################### 
    private Integer topicId;
    private String name;
    private Integer allPosts;
    private Integer openPosts;
    private Timestamp lastPost;
    private Integer activeUsers;
    
    // ####################### Constructors #################### 
    public Topic(){
        
    }

    public Topic (Integer topicId, String name, Integer allPosts, Integer openPosts, Timestamp lastPost, Integer activeUsers){
        this.topicId = topicId;
        this.name = name;
        this.allPosts = allPosts;
        this.openPosts = openPosts;
        this.lastPost = lastPost;
        this.activeUsers = activeUsers;
    }
    // ####################### Other Methods ####################
    public static ArrayList<Topic> collectAllTopics(){
        ArrayList<Topic> topics = new ArrayList<>();

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");) {
            String query = "select * from topics";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                topics.add(new Topic(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),rs.getInt(6)));
            }
            
        } catch (ClassCastException|SQLException e) {
            e.printStackTrace(); 
        }
        return topics;
    }
    
    // ####################### Getters-Setters ####################
    public Integer getTopicId(){
        return topicId;
    } 

    public void setTopicId(Integer topicId){
        this.topicId = topicId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

     public Integer getAllPosts(){
        return allPosts;
    }

    public void setAllPosts(Integer allPosts){
        this.allPosts = allPosts;
    }

    public Integer getOpenPosts(){
        return openPosts;
    }

    public void setOpenPosts(Integer openPosts){
        this.openPosts = openPosts;
    }

    public Integer getActiveUsers(){
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers){
        this.activeUsers = activeUsers;
    }

    public Timestamp getLastPost() {
        return lastPost;
    }

    public void setLastPost(Timestamp lastPost) {
        this.lastPost = lastPost;
    }

}
