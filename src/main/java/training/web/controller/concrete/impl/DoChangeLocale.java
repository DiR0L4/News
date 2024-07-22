package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.controller.concrete.Command;

import java.io.IOException;
import java.util.Locale;

import static training.web.controller.constant.Parameters.*;

public class DoChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter(REQUEST_LANG);
        request.getSession().setAttribute(SESSION_LOCALE, new Locale(lang));
        response.sendRedirect(request.getHeader(REFERER));
    }
}
