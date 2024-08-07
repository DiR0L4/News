package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import training.web.controller.concrete.Command;

import java.io.IOException;

import static training.web.controller.constant.Parameters.SESSION_USER;

public class DoLogout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false);

        if (session != null && session.getAttribute(SESSION_USER) != null) {
            session.removeAttribute(SESSION_USER);
        }
        response.sendRedirect("MyController?command=go_to_auth&authError=You've been unlogged!");
    }
}
