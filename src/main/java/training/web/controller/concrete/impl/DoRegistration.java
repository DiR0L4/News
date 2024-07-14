package training.web.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.bean.RegistrationInfo;
import training.web.controller.concrete.Command;
import training.web.dao.exception.EmailAlreadyExistsException;
import training.web.service.ServiceException;
import training.web.service.ServiceProvider;
import training.web.service.UserService;
import training.web.service.exception.ValidationException;
import training.web.service.util.Validator;

import java.io.IOException;

public class DoRegistration implements Command {
    UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        String roleId = request.getParameter("role");
        if(roleId == null){
            roleId = "3";
        }

        RegistrationInfo registrationInfo = new RegistrationInfo(login, name, email, surname, country, phone, password, passwordConfirmation, Integer.parseInt(roleId));

        try {
            userService.registration(registrationInfo);
            response.sendRedirect("MyController?command=go_to_auth&authMessage=Registration was successful, now you can log in");
        }catch (ServiceException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=Error occurred during registration");
        }catch (EmailAlreadyExistsException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=User with email " + email + " already exists");
        }catch (ValidationException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=Fields are filled incorrectly");
        }

    }
}
