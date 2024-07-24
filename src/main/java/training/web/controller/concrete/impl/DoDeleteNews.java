package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.bean.News;
import training.web.bean.User;
import training.web.controller.concrete.Command;
import training.web.service.NewsService;
import training.web.service.ServiceException;
import training.web.service.ServiceProvider;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;
import static training.web.controller.constant.Parameters.SESSION_USER;

public class DoDeleteNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter(REQUEST_NEWS_ID));

        HttpSession session = (HttpSession) request.getSession(false);

        User user = (User) session.getAttribute(SESSION_USER);
        if(session.getAttribute(SESSION_USER) != null && user.getRoleId() == 1) {
            try {
                newsService.deleteNews(newsId);
                response.sendRedirect("MyController?command=go_to_main_page");
            } catch (ServiceException e) {
                response.sendRedirect("MyController?command=go_to_error_page&error=Error occurred during trying to delete news");
            }
        }else {
            response.sendRedirect("MyController?command=go_to_auth&authError=You cannot perform this action. Please log in!");
        }
    }
}
