package training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import training.web.bean.RegistrationInfo;
import training.web.controller.concrete.Command;
import training.web.service.exception.ServiceException;
import training.web.service.ServiceProvider;
import training.web.service.UserService;
import training.web.service.exception.EmailAlreadyExistsServiceException;
import training.web.service.exception.ValidationException;

import java.io.IOException;

import static training.web.controller.constant.Parameters.*;

public class DoRegistration implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(REQUEST_USER_NAME);
        String surname = request.getParameter(REQUEST_USER_SURNAME);
        String login = request.getParameter(REQUEST_USER_LOGIN);
        String email = request.getParameter(REQUEST_USER_EMAIL);
        String country = request.getParameter(REQUEST_USER_COUNTRY);
        String phone = request.getParameter(REQUEST_USER_PHONE);
        String password = request.getParameter(REQUEST_USER_PASSWORD);
        String passwordConfirmation = request.getParameter(REQUEST_USER_PASSWORD_CONFIRMATION);
        String roleId = request.getParameter(REQUEST_USER_ROLE);
        if(roleId == null){
            roleId = "3";
        }

        RegistrationInfo registrationInfo = new RegistrationInfo(login, name, email, surname, country, phone, password, passwordConfirmation, Integer.parseInt(roleId));

        try {
            userService.registration(registrationInfo);
            response.sendRedirect("MyController?command=go_to_auth&authMessage=Registration was successful, now you can log in");
        }catch (EmailAlreadyExistsServiceException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=User with email " + email + " already exists");
        }catch (ServiceException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=Error occurred during registration");
        }catch (ValidationException e){
            response.sendRedirect("MyController?command=go_to_registration_page&regError=Fields are filled incorrectly");
        }

    }
}
