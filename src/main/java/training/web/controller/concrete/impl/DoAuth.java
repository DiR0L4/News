package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.controller.concrete.Command;

import training.web.bean.User;
import training.web.bean.AuthInfo;
import training.web.service.ServiceException;
import training.web.service.ServiceProvider;
import training.web.service.UserService;
import training.web.service.exception.ValidationException;

import java.io.IOException;

import static training.web.controller.constant.Parameters.REQUEST_REMEMBER_ME;
import static training.web.controller.constant.Parameters.SESSION_USER;

public class DoAuth implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("authEmail");
        String password = request.getParameter("authPassword");

        try{
            User user = userService.authorization(new AuthInfo(email, password));

            if(user != null) {
                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute(SESSION_USER, user);

                String rememberMe = request.getParameter(REQUEST_REMEMBER_ME);
                if (rememberMe != null) {
                    Cookie cookie = new Cookie(REQUEST_REMEMBER_ME, user.getLogin());
                    response.addCookie(cookie);
                }
                response.sendRedirect("MyController?command=go_to_main_page");

            }else {
                response.sendRedirect("MyController?command=go_to_auth&authError=Wrong email or password!");
            }
        }catch (ServiceException e){
            response.sendRedirect("MyController?command=go_to_auth&authError=Error occurred during authorization");
        }catch (ValidationException e){
            response.sendRedirect("MyController?command=go_to_auth&authError=The fields are filled incorrectly");
        }
    }
}
