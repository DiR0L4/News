package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.bean.News;
import training.web.bean.User;
import training.web.controller.concrete.Command;
import training.web.service.NewsService;
import training.web.service.exception.ServiceException;
import training.web.service.ServiceProvider;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;
public class DoAddNews implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter(REQUEST_NEWS_TITLE);
        String image = request.getParameter(REQUEST_NEWS_IMAGE);
        String brief = request.getParameter(REQUEST_NEWS_BRIEF);
        String info = request.getParameter(REQUEST_NEWS_INFO);
        int tag = Integer.parseInt(request.getParameter(REQUEST_NEWS_TAG));

        HttpSession session = (HttpSession) request.getSession(false);

        if(session != null && session.getAttribute(SESSION_USER) != null) {

            User user = (User) session.getAttribute(SESSION_USER);
            if(user.getRoleId() == 1 || user.getRoleId() == 2) {
                try {
                    newsService.addNews(new News(0, title, brief, info, image, tag));
                    response.sendRedirect("MyController?command=go_to_add_news_page&newsMessage=News added!");
                } catch (ServiceException e) {
                    response.sendRedirect("MyController?command=go_to_add_news_page&newsError=Error occurred during trying to add news");
                }
            }
            else {
                response.sendRedirect("MyController?command=go_to_error_page&error=You cannot perform this action. Access denied!");
            }

        }else {
            response.sendRedirect("MyController?command=go_to_auth&authError=You cannot perform this action. Please log in!");
        }
    }
}
