package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

import models.User;

@WebServlet("/check_email_exists.do")
public class CheckEmailExistsServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String email = request.getParameter("email");

        boolean flag = false;

        
        flag = User.checkEmailExists(email);
        
        response.getWriter().print(flag);

    }
}