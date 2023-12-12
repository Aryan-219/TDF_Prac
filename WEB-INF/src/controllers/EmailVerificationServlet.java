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
        System.out.println("Starting email verification");
        System.out.println(email);
        System.out.println(otp);
        String nextPage = "error1.jsp"; // TODO
        int result =-1;
        result = User.verifyEmail(email, otp);
        if (result==1) {
            System.out.println("updating next page to signin page");
            nextPage = "signin.jsp";
        }else if(result == 0){
            nextPage ="emailverified.jsp";
        }else{
            System.out.println("dikkat agyi bro");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
