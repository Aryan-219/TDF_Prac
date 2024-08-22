package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class Status {
    // ########### Properties #################
    public static ServletContext appContext;
    public static String conURL;

    private Integer statusId;
    private String name;

    // ############### Constants #############
    public static final Status ACTIVE = new Status(1, "Active");
    public static final Status INACTIVE = new Status(2, "Inactive");
    public static final Status OPEN = new Status(3, "Open");
    public static final Status CLOSED = new Status(4, "Closed");
    public static final Status BLOCKED = new Status(5, "Blocked");

    // ########### Constructors ###############
    public Status() {

    }

    public Status(Integer statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    // ########### Other Methods ##############
    public static ArrayList<Status> collectAllStatus() {
        ArrayList<Status> status = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(conURL);

            String query = "select * from status";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                status.add(new Status(rs.getInt(1), rs.getString(2)));
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return status;
    }

    // ########### Getter-Setters ##############
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}