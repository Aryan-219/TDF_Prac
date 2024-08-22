package listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import java.util.ArrayList;

import utils.AppUtility;

import models.*;

public class AppListenerImpl implements ServletContextListener {
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();
        String[] models = { "Badge", "Country", "Message", "Moderator", "Occupation", "Post", "Question", "Reply",
                "Status", "Topic", "User", "UserType", "Wishlist" };

        String host = context.getInitParameter("host");
        String unmSql = context.getInitParameter("unmSql");
        String pwdSql = context.getInitParameter("pwdSql");
        String dbName = context.getInitParameter("dbName");
        String port = context.getInitParameter("port");

        String conURL = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + unmSql + "&password=" + pwdSql;
        AppUtility.appContext = context;

        for (String modelClass : models) {
            try {
                Class<?> modelClassObj = Class.forName("models." + modelClass);
                java.lang.reflect.Field appContextField = modelClassObj.getField("appContext");
                java.lang.reflect.Field connectionURL = modelClassObj.getField("conURL");
                connectionURL.set(null, conURL);
                appContextField.set(null, context);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        AppUtility.fromEmail = context.getInitParameter("from_email");
        AppUtility.fromEmailPassword = context.getInitParameter("from_email_password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("############################################");
        System.out.println("############## TDF Starting ###############");
        System.out.println("############################################");
        System.out.println(" -------------- --------------- -------------");
        System.out.println(" -------------- Data Loading -------------");

        System.out.println(" -------------- --------------- -------------");
        System.out.println(" -------------- Countries -------------");
        ArrayList<Country> countries = Country.collectAllCountries();
        context.setAttribute("countries", countries);

        System.out.println(" -------------- --------------- -------------");
        System.out.println(" -------------- Badges -------------");
        ArrayList<Badge> badges = Badge.collectAllBadges();
        context.setAttribute("badges", badges);

        System.out.println(" -------------- --------------- -------------");
        System.out.println(" -------------- Occupations -------------");
        ArrayList<Occupation> occupations = Occupation.collectAllOccupations();
        context.setAttribute("occupations", occupations);

        System.out.println(" -------------- --------------- -------------");
        System.out.println(" -------------- Status -------------");
        ArrayList<Status> status = Status.collectAllStatus();
        context.setAttribute("status", status);

        System.out.println("############################################");
        System.out.println("############## TDF Started ###############");
        System.out.println("############################################");
    }

    public void contextDestroyed(ServletContextEvent ev) {

    }
}
