package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Question;

@WebServlet("/question_details.do")
public class QuestionDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int questionId = Integer.parseInt(request.getParameter("question_id"));

        Question question = new Question(questionId);
        question.getDetails();
        System.out.println(question.getPostedOn() + " ~~~~~~~~~~~~~ ##########");

        request.setAttribute("question", question);

        request.getRequestDispatcher("question_details.jsp").forward(request, response);
    }
}