package models;

import javax.servlet.ServletContext;

public class UserType {
    public static ServletContext appContext;
    public static String conURL;

    private Integer userTypeId;
    private String name;

    public UserType() {
    }

    public UserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}