package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.bean.News;
import training.web.controller.concrete.Command;
import training.web.logic.LogicStub;

import java.io.IOException;
import java.util.List;

public class GoToIndexPage implements Command {
    private final LogicStub logic = new LogicStub();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> mainNews  = logic.lastNews();
        request.setAttribute("mainNews", mainNews);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_index.jsp");
        dispatcher.forward(request, response);
    }
}
