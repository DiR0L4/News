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
import java.util.List;

import static training.web.controller.constant.Parameters.*;

public class DoNewsByTag implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);

        try {
            int tagId = Integer.parseInt(request.getParameter(REQUEST_NEWS_TAG));

            List<News> news = newsService.getNewsByTagId(tagId);
            request.setAttribute(REQUEST_TAG_NEWS, news);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news_by_tags.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e){
            response.sendRedirect("MyController?command=go_to_error_page&error=Error occurred during trying to get news");
        }
    }
}
