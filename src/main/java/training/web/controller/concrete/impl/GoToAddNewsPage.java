package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.bean.User;
import training.web.controller.concrete.Command;

import java.io.IOException;

import static training.web.controller.constant.Parameters.SESSION_USER;

public class GoToAddNewsPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);

        User user = (User) session.getAttribute(SESSION_USER);
        if(session != null && user != null) {

            if(user.getRoleId() == 1 || user.getRoleId() == 2){
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/add_news.jsp");
                dispatcher.forward(request, response);
            }
            else {
                response.sendRedirect("MyController?command=go_to_error_page&error=You cannot perform this action. Access denied!");
            }

        }else {
            response.sendRedirect("MyController?command=go_to_auth&newsError=You cannot perform this action. Please log in!");
        }
    }
}
