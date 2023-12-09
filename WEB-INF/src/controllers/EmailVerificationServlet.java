package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import models.User;

@WebServlet("/evf.do")
public class EmailVerificationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String otp = request.getParameter("vcode");
        System.out.println(email);
        System.out.println(otp);
        String nextPage = "error.jsp"; // TODO
        if (User.verifyEmail(email, otp)) {
            System.out.println("2nd time flag");
            nextPage = "signin.jsp";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
