package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.controller.concrete.Command;
import training.web.logic.LogicStub;

import training.web.bean.User;
import training.web.bean.AuthInfo;

import java.io.IOException;

public class DoAuth implements Command {
    private final LogicStub logic = new LogicStub();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("authEmail");
        String password = request.getParameter("authPassword");

        System.out.println("Perform user authentication and authorization. Login: " + login);
        User user = logic.checkAuth(new AuthInfo(login, password));

        if(user != null) {
            HttpSession session = (HttpSession) request.getSession(true);
            session.setAttribute("user", user);

            String rememberMe = request.getParameter("remember-me");
            if (rememberMe != null) {
                Cookie cookie = new Cookie("remember-me", user.getId()+""); //attention, realization is not safe
                response.addCookie(cookie);
            }

            response.sendRedirect("MyController?command=go_to_main_page");

        }else {
            response.sendRedirect("MyController?command=go_to_auth&authError=Wrong login or password!");
        }
    }
}
