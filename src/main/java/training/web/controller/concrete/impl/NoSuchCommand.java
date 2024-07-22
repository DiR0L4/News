package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.controller.concrete.Command;

import java.io.IOException;

import static training.web.controller.constant.Parameters.REQUEST_ERROR;

public class NoSuchCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("MyController?command=go_to_error_page&error=Bad request");
    }
}
