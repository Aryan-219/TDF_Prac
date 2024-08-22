package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Question;

@WebServlet("/topic_questions.do")
public class TopicQuestionsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        
        int topicId = Integer.parseInt(request.getParameter("topic_id"));

        ArrayList<Question> questions = Question.collectAllQuestions(topicId);
        
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("questions.jsp").forward(request, response);
    }
}
