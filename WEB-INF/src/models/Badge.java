package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Badge {
    // ###############################Properties ######################
    private Integer badgeId;
    private String name;

    // ############################### Constructors ######################
    public Badge() {
        
    }
    
    public Badge(Integer badgeId, String name) {
        this.badgeId = badgeId;
        this.name = name;
    }

    // ############################### Other Methods ######################
    public static ArrayList<Badge> collectAllBadges(){
        ArrayList<Badge> badges = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");

            String query = "select * from badges";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs =  ps.executeQuery();

            while (rs.next()) {
                badges.add(new Badge(rs.getInt(1),rs.getString(2)));
            }
            
            con.close();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();        
        }
        return badges;
    }

    // #####################Getters-Setters ########################
    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
