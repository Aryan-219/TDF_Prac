package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

@WebServlet("/evf.do")
public class EmailVerificationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String vcode = request.getParameter("vcode");

        String nextPage = "error.jsp";  //TODO

        if(User.verifyEmail(email, vcode)) {
            nextPage = "signin.jsp";
        }

        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
