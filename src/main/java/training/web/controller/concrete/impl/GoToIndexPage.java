package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.bean.News;
import training.web.controller.concrete.Command;

import java.io.IOException;

public class GoToIndexPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News news = new News("Great", "Homework is done");
        request.setAttribute("mainNews", news);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_index.jsp");
        dispatcher.forward(request, response);
    }
}
