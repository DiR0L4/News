package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.bean.News;
import training.web.bean.User;
import training.web.controller.concrete.Command;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;

public class GoToUpdateNewsPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);

        User user = (User) session.getAttribute(SESSION_USER);
        if(user != null && user.getRoleId() == 1) {

            /*int newsId = Integer.parseInt(request.getParameter(REQUEST_NEWS_ID));
            request.setAttribute(REQUEST_NEWS_ID, newsId);*/

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/add_news.jsp");
            dispatcher.forward(request, response);

        }else {
            response.sendRedirect("MyController?command=go_to_error_page&error=You cannot perform this action. Access denied!");
        }
    }
}
