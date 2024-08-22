package models;

import java.sql.*;

import javax.servlet.ServletContext;

public class Moderator {
    public static ServletContext appContext;
    public static String conURL;

    private Integer moderatorId;
    private Topic topic;
    private User user;
    private Timestamp joinedOn;
    private Status status;

    public Moderator() {
    }

    public static boolean isTopicsModerator(Integer userId, Integer topicId) {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select * from moderators where user_id=? and topic_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, topicId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                flag = true;
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public Integer getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Integer moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Timestamp joinedOn) {
        this.joinedOn = joinedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
