package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.AppUtility;
import utils.SMSSender;

@WebServlet("/generate_otp.do")
public class GenerateOTPServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();

        String phone  = request.getParameter("phone");

        String otp = AppUtility.generateOTP();
        
        SMSSender.sendSMS(phone,otp);

        session.setAttribute("otp", otp);
        session.setAttribute("otp", "444444");

        response.getWriter().print(true);
        
    }
}