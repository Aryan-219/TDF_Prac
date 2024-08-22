package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class Question extends Post {
    public static ServletContext appContext;
    public static String conURL;

    private Integer questionId;
    private Topic topic;
    private String title;
    // private Post post;
    private Integer replies;

    // ------------ constructors --------------
    public Question() {

    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Question(User user, Timestamp postedOn, String post, Boolean postType, Topic topic, String title) {
        super(user, postedOn, post, postType);
        this.topic = topic;
        this.title = title;
    }

    // -------------other methods ---------------
    public void getDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select question_id,title,replies,q.post_id,u.user_id,u.name,posted_on,post," +
                    "likes,dislikes,spams,shares,post_type,s.status_id,s.name,t.topic_id,t.name from questions as q " +
                    "inner join posts as p inner join users as u inner join status as s inner join topics as t where q.question_id=? "
                    +
                    "and q.post_id=p.post_id and p.user_id=u.user_id and p.status_id=s.status_id and t.topic_id=q.topic_id and "
                    +
                    "s.status_id!=5";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, questionId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                questionId = rs.getInt(1);
                title = rs.getString(2);
                replies = rs.getInt(3);
                setPostId(rs.getInt(4));
                setUser(new User(rs.getInt(5), rs.getString(6)));
                setPostedOn(rs.getTimestamp(7));
                setPost(rs.getString(8));
                setLikes(rs.getInt(9));
                setDislikes(rs.getInt(10));
                setSpams(rs.getInt(11));
                setShares(rs.getInt(12));
                setPostType(rs.getBoolean(13));
                setStatus(new Status(rs.getInt(14), rs.getString(15)));
                topic = new Topic(rs.getInt(16), rs.getString(17));
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveQuestion() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "insert into questions (topic_id, title, post_id) value (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, topic.getTopicId());
            ps.setString(2, title);
            ps.setInt(3, getPostId());

            int val = ps.executeUpdate();

            if (val == 1)
                flag = true;

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static ArrayList<Question> collectAllQuestions(Integer topicId) {
        ArrayList<Question> questions = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select question_id,title,replies,q.post_id,u.user_id,u.name,posted_on,post," +
                    "likes,dislikes,spams,shares,post_type,s.status_id,s.name from questions as q " +
                    "inner join posts as p inner join users as u inner join status as s where q.topic_id=? " +
                    "and q.post_id=p.post_id and p.user_id=u.user_id and p.status_id=s.status_id and " +
                    "s.status_id!=5 order by p.posted_on desc";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, topicId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.questionId = rs.getInt(1);
                question.title = rs.getString(2);
                question.replies = rs.getInt(3);
                question.setPostId(rs.getInt(4));
                question.setUser(new User(rs.getInt(5), rs.getString(6)));
                question.setPostedOn(rs.getTimestamp(7));
                String post = rs.getString(8);
                if (post.length() > 150) {
                    post = post.substring(0, 150);
                    post += " read more...";
                }
                question.setPost(post);
                question.setLikes(rs.getInt(9));
                question.setDislikes(rs.getInt(10));
                question.setSpams(rs.getInt(11));
                question.setShares(rs.getInt(12));
                question.setPostType(rs.getBoolean(13));
                question.setStatus(new Status(rs.getInt(14), rs.getString(15)));

                questions.add(question);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // ----------------getter-setters-------------------
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

    // public Post getPost() {
    // return post;
    // }
    // public void setPost(Post post) {
    // this.post = post;
    // }
    public Integer getReplies() {
        return replies;
    }

    public void setReplies(Integer replies) {
        this.replies = replies;
    }

}
