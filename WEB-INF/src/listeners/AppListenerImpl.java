package listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import java.util.ArrayList;

import models.Badge;
import models.Country;
import models.Occupation;
import models.Status;
import utils.AppUtility;
// import models.Topic;

public class AppListenerImpl implements ServletContextListener{
    public void contextInitialized(ServletContextEvent ev){
        ServletContext context = ev.getServletContext();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("#####################################");
        System.out.println("##### MY TDF Starting #####");
        System.out.println("~~~~~ Data loading in progress ~~~~~");
        
        System.out.println("----------Countries-----------");
        ArrayList<Country> countries = Country.collectAllCountries();
        context.setAttribute("countries", countries);
        System.out.println("--------------------------------");
        
        System.out.println("----------Badges-----------");
        ArrayList<Badge> badges = Badge.collectAllBadges();
        context.setAttribute("badges", badges);
        System.out.println("--------------------------------");
        
        System.out.println("---------- Occupations -----------");
        ArrayList<Occupation> occupations = Occupation.collectAllOccupations();
        context.setAttribute("occupations", occupations);
        System.out.println("--------------------------------");
        
        System.out.println("----------Status-----------");
        ArrayList<Status> status = Status.collectAllStatus();
        context.setAttribute("status", status);
        System.out.println("--------------------------------");
        /* 
        System.out.println("--------- Topics ----------");
        ArrayList<Topic> topics = Topic.collectAllTopics();
        context.setAttribute("topics", topics);
        System.out.println("--------------------------------");
*/
        AppUtility.appContext = context;
        AppUtility.fromEmail = context.getInitParameter("from_email");
        AppUtility.fromEmailPassword = context.getInitParameter("from_email_password");

        System.out.println("##### MY TDF Started #####");
        System.out.println("#####################################");
    }

    public void contextDestroyed (ServletContextEvent ev){
        System.out.println("Servlet Context will now get destroyed..");
    }
}