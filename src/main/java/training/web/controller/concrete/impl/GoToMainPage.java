package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.bean.News;
import training.web.controller.concrete.Command;
import training.web.controller.constant.Parameters.*;
import training.web.service.NewsService;
import training.web.service.ServiceException;
import training.web.service.ServiceProvider;

import java.io.IOException;
import java.util.List;

import static training.web.controller.constant.Parameters.REQUEST_LAST_NEWS;

public class GoToMainPage implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = (HttpSession) request.getSession(false);

        if(session != null && session.getAttribute("user") != null) {


        }else {
            response.sendRedirect("MyController?command=go_to_auth&authError=You cannot perform this action. Please log in!");
        }*/

        try {
            List<News> lastNews = newsService.getLastNews();
            request.setAttribute(REQUEST_LAST_NEWS, lastNews);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e){
            // Сделать страницу 404
        }

    }
}
