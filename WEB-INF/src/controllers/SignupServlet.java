package controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.AppUtility;
import models.User;
import models.Country;
import utils.EmailSender;

@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String responseToken = request.getParameter("g-recaptcha-response");

        //######################################################
        ServletContext context = getServletContext();

        String recaptchaURL = context.getInitParameter("recaptcha_url");
        String secretKey = context.getInitParameter("secret_key");

        boolean flag = AppUtility.checkGoogleRecaptchaResponse(recaptchaURL, secretKey, responseToken);
        //######################################################
        
        String nextPage = "signup.jsp";

        if(flag) {
            //get parameters
            String name = request.getParameter("full_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int countryId = Integer.parseInt(request.getParameter("country"));
            String phone = request.getParameter("phone");

            String otp = AppUtility.generateOTP();

            User user = new User(name, email, password, phone, new Country(countryId), otp);

            if(user.signupUser()) {
                String uploadLocation = getServletContext().getRealPath("/WEB-INF/uploads");

                File userFolder = new File(uploadLocation, email);
                userFolder.mkdir();
                 
                //send email
                EmailSender.sendAccVerificationMail(email, otp);
                nextPage = "signup_success.jsp";
            }
        }

        response.sendRedirect(nextPage);
    }
}
