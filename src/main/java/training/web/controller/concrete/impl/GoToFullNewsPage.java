package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.bean.News;
import training.web.controller.concrete.Command;
import training.web.service.NewsService;
import training.web.service.exception.ServiceException;
import training.web.service.ServiceProvider;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;

public class GoToFullNewsPage implements Command {
    private final static NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = (HttpSession) request.getSession(false);

            if (session != null && session.getAttribute(SESSION_USER) != null) {
                int newsId = Integer.parseInt(request.getParameter(REQUEST_NEWS_ID));

                News news = newsService.getNewsById(newsId);
                request.setAttribute(REQUEST_NEWS, news);

                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/full_news.jsp");
                dispatcher.forward(request, response);
            }
            else {
                response.sendRedirect("MyController?command=go_to_auth&authError=You cannot perform this action. Please log in!");
            }
        } catch (ServiceException e){
            response.sendRedirect("MyController?command=go_to_error_page&error=Internal server error");
        }
    }
}
