package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Topic;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Topic> topics = Topic.collectAllTopics();

        request.setAttribute("topics", topics);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
