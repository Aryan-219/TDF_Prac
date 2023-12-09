package models;

import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Occupation {
    // ####################### Properties ########################
    private Integer occupationId;
    private String name;
    
    // ####################### Constants ########################
    public static final Occupation STUDENT = new Occupation(1, "Student");
    public static final Occupation WORKING_PROFESSIONAL = new Occupation(2, "Working Professional");
    public static final Occupation STARTUP_OWNER = new Occupation(3,"Startup Owner");
    public static final Occupation FREELANCER = new Occupation(4, "Freelancer");

    // ####################### Constructors ########################
    public Occupation(){
        
    }

    public Occupation(Integer occupationId, String name){
        this.occupationId = occupationId;
        this.name= name;
    }
    
    // ####################### Other Methods ########################
    public static ArrayList<Occupation> collectAllOccupations(){
        ArrayList<Occupation> occupations = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytdf?user=root&password=1234");

            String query = "select * from occupations";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                occupations.add(new Occupation(rs.getInt(1), rs.getString(2)));
            }

            con.close();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

        return occupations;
    }

    // ####################### Getters-Setters ########################
    public Integer getOccupationId(){
        return occupationId;
    }
    public void setOccupationId(Integer occupationId){
        this.occupationId = occupationId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
