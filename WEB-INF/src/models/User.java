package models;

import java.sql.*;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class User {
    static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

    // ########### Properties #################
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String pic;
    private String phone;
    private String interest;
    private String employer;
    private Integer experience;
    private String jobProfile;
    private Occupation occupation;
    private String technologiesUsed;
    private Integer questionsPosted;
    private Integer responses;
    private Country country;
    private Integer messagesBlocked;
    private String otp;
    private Integer starRank;
    private Badge badge;
    private Status status;

    // ########### Constructors ###############
    public User() {

    }

    public User(String name, String email, String password, String phone, Country country) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.country = country;
    }

    public User(String name, String email, String password, String phone, Country country, String otp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.country = country;
        this.otp = otp;
    }

    // ########### Other Methods ##############
    public static boolean checkPhoneExists(String phone) {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");
            String query = "select user_id from users where phone=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flag = true;
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean checkEmailExists(String email) {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");
            String query = "select  user_id from users where email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Duplicate entry found");
                flag = true;
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int verifyEmail(String email, String otp) {
        int x = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");

            String query = "update users set status_id=1,otp='' where email=? and otp=? and status_id!=1";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, otp);

            int res = ps.executeUpdate();
            System.out.println("Result of the update query" + res);
            if (res == 1) {
                System.out.println("email verification successful");
                System.out.println("Updating flag value to true");
                x = 1;
            }else{
                x = 0;
                System.out.println("You are already verified");
            }
            
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public boolean signupUser() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");

            String query = "insert into users (name, email, password, phone, country_id,otp) value (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, spe.encryptPassword(password));
            ps.setString(4, phone);
            ps.setInt(5, country.getCountryId());
            ps.setString(6, otp);
            int res = ps.executeUpdate();

            if (res == 1)
                flag = true;

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    // ########### Getter-Setters ##############
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile) {
        this.jobProfile = jobProfile;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public Integer getQuestionsPosted() {
        return questionsPosted;
    }

    public void setQuestionsPosted(Integer questionsPosted) {
        this.questionsPosted = questionsPosted;
    }

    public Integer getResponses() {
        return responses;
    }

    public void setResponses(Integer responses) {
        this.responses = responses;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getMessagesBlocked() {
        return messagesBlocked;
    }

    public void setMessagesBlocked(Integer messagesBlocked) {
        this.messagesBlocked = messagesBlocked;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Integer getStarRank() {
        return starRank;
    }

    public void setStarRank(Integer starRank) {
        this.starRank = starRank;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
