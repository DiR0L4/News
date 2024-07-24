package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.controller.concrete.Command;
import training.web.service.ServiceProvider;
import training.web.service.UserService;

import java.io.IOException;

public class GoToNewsByTagsPage implements Command {

    private final static UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news_by_tags.jsp");
        dispatcher.forward(request, response);
    }
}
