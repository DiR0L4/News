package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.maven.model.Profile;
import training.web.bean.News;
import training.web.bean.User;
import training.web.bean.UserProfile;
import training.web.controller.concrete.Command;
import training.web.service.ServiceProvider;
import training.web.service.UserService;
import training.web.service.exception.ServiceException;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;

public class GoToProfilePage implements Command {

    private final static UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = (HttpSession) request.getSession(false);

            User user = (User) session.getAttribute(SESSION_USER);
            if (session != null && user != null) {
                UserProfile profile = userService.getUserProfile(user.getId());
                request.setAttribute(REQUEST_PROFILE, profile);

                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
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
