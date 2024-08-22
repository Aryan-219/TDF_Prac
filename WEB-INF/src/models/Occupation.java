package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class Occupation {
    // ########### Properties #################
    public static ServletContext appContext;
    public static String conURL;

    private Integer occupationId;
    private String name;

    // ############### Constants #############
    public static final Occupation STUDENT = new Occupation(1, "Student");
    public static final Occupation WORKING_PROFESSIONAL = new Occupation(2, "Working Professional");
    public static final Occupation STARTUP_OWNER = new Occupation(3, "Startup Owner");
    public static final Occupation FREELANCER = new Occupation(4, "Freelancer");

    // ########### Constructors ###############
    public Occupation() {

    }

    public Occupation(Integer occupationId) {
        this.occupationId = occupationId;
    }

    public Occupation(Integer occupationId, String name) {
        this.occupationId = occupationId;
        this.name = name;
    }

    // ########### Other Methods ##############
    public static ArrayList<Occupation> collectAllOccupations() {
        ArrayList<Occupation> occupations = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select * from occupations";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                occupations.add(new Occupation(rs.getInt(1), rs.getString(2)));
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return occupations;
    }

    // ########### Getter-Setters ##############
    public Integer getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Integer occupationId) {
        this.occupationId = occupationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
