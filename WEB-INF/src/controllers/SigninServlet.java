package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

@WebServlet("/signin.do")
public class SigninServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nextUrl = request.getParameter("next_url");

        User user = new User(email, password);
        int statusId = user.signinUser();

        String nextPage = "signin.jsp";
        boolean flag = false;

        switch(statusId) {
            case -1:
                request.setAttribute("signin_err_msg", "Incorrect Password...");
                break;
            case 0:
                request.setAttribute("signin_err_msg", "Account with the given email does not exist...");
                break;
            case 1:
                //------ Plan After Login Page
                session.setAttribute("user", user);
                if(!nextUrl.equals(""))
                    nextPage = nextUrl;
                else
                    nextPage = "profile.do";
                flag = true;
                break;
            case 2:
                request.setAttribute("signin_err_msg", "Verify Your account by clicking over the Activation Link sent to your email id... resend Verification Mail <link>");
                break;
            case 4:
                request.setAttribute("signin_err_msg", "Your Account is Closed... Activate your account <link>");
                break;
            case 5:
                request.setAttribute("signin_err_msg", "Sorry! Your Account is Blocked...");
                break;
        }

        if(flag)
            response.sendRedirect(nextPage);
        else
            request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
