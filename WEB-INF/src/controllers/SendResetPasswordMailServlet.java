package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.EmailSender;

@WebServlet("/send_reset_password_mail.do")
public class SendResetPasswordMailServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");

        boolean flag = false;

        if(User.checkEmailExists(email)) {
            flag = true;
            EmailSender.sendResetPasswordMail(email);
        }

        response.getWriter().print(flag);
    }
}
