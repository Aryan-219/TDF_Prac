package models;

import java.sql.*;

import javax.servlet.ServletContext;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User {
    static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

    // ########### Properties #################
    public static ServletContext appContext;
    public static String conURL;

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
    private UserType userType;

    // ########### Constructors ###############
    public User() {

    }

    public User(String name, String email, String password, String phone, Country country, String otp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.country = country;
        this.otp = otp;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // ########### Other Methods ##############
    public void saveInterest(String interest) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "update users set interest=? where user_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, interest);
            ps.setInt(2, userId);

            int val = ps.executeUpdate();

            if (val == 1)
                this.interest = interest;

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void savePic(String fileName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "update users set pic=? where user_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email + "/" + fileName);
            ps.setInt(2, userId);

            int val = ps.executeUpdate();

            if (val == 1) {
                this.pic = email + "/" + fileName;
                System.out.println("+++++++++ $$$ ++++++++++");
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updatePassword(String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "update users set password=? where email=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, spe.encryptPassword(password));
            ps.setString(2, email);

            ps.executeUpdate();

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int signinUser() {
        int statusId = 0;
        // statusId:
        // 0 - ac with email does not exist...
        // -1 - password mismatched...
        // 1 - active
        // 2,4,5 - inactive,closed & blocked

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select user_id,u.name,password,pic,phone,interest,employer,experience,"
                    + "job_profile,occupation_id,technologies_used,questions_posted,responses,c.country_id,c.name,"
                    + "messages_blocked,star_rank,badge_id,s.status_id,s.name,user_type_id from users as u inner join countries as c "
                    + "inner join status as s where email=? and u.country_id=c.country_id and u.status_id=s.status_id";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                statusId = rs.getInt("status_id");

                if (statusId == 1) {
                    if (spe.checkPassword(password, rs.getString("password"))) {
                        password = null;
                        userId = rs.getInt("user_id");
                        name = rs.getString("name");
                        pic = rs.getString("pic");
                        phone = rs.getString("phone");
                        interest = rs.getString("interest");
                        employer = rs.getString("employer");
                        experience = rs.getInt("experience");
                        jobProfile = rs.getString("job_profile");
                        occupation = new Occupation(rs.getInt("occupation_id"));
                        technologiesUsed = rs.getString("technologies_used");
                        questionsPosted = rs.getInt("questions_posted");
                        responses = rs.getInt("responses");
                        country = new Country(rs.getInt("country_id"), rs.getString(15));
                        messagesBlocked = rs.getInt("messages_blocked");
                        starRank = rs.getInt("star_rank");
                        badge = new Badge(rs.getInt("badge_id"));
                        status = new Status(rs.getInt(19), rs.getString(20));
                        userType = new UserType(rs.getInt(21));
                    } else {
                        statusId = -1;
                    }
                }
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return statusId;
    }

    public static boolean checkPhoneExists(String phone) {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select user_id from users where phone=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone);

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

    public static boolean checkEmailExists(String email) {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select user_id from users where email=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

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

    public static boolean verifyEmail(String email, String vcode) {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "update users set status_id=1,otp='' where email=? and otp=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, vcode);

            int res = ps.executeUpdate();

            if (res == 1) {
                flag = true;
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public boolean signupUser() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "insert into users (name, email, password, phone, country_id, otp) value (?,?,?,?,?,?)";
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
