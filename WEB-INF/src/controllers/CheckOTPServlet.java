package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check_otp.do")
public class CheckOTPServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        HttpSession session = request.getSession();

        String otp1 =  request.getParameter("otp");
        String otp2 = (String) session.getAttribute("otp");
        boolean flag = false;
        if(otp1.equals(otp2)){
            flag = true;
        }
        response.getWriter().print(flag);
    }
}
