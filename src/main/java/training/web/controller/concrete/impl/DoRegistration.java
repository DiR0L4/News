package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.controller.concrete.Command;

import java.io.IOException;

public class DoRegistration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("name " + request.getParameter("name"));
        System.out.println("surname " + request.getParameter("surname"));
        System.out.println("city " + request.getParameter("city"));
        System.out.println("country " + request.getParameter("country"));
        System.out.println("login " + request.getParameter("login"));
        System.out.println("password " + request.getParameter("password"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
