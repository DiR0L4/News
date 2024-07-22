package training.web.service.impl;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.dao.DAOException;
import training.web.dao.DAOProvider;
import training.web.dao.UserDAO;
import training.web.dao.exception.EmailAlreadyExistsException;
import training.web.service.ServiceException;
import training.web.service.UserService;
import training.web.service.exception.ValidationException;
import training.web.service.util.Validator;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = DAOProvider.getInstance().getUserDao();
    Validator validator = new Validator();
    @Override
    public boolean registration(RegistrationInfo regInfo) throws ServiceException, EmailAlreadyExistsException, ValidationException {
        try {
            if(validator.validateRegistration(regInfo))
            {
                return userDAO.addUser(regInfo);
            }
            else {
                throw new ValidationException("The fields are filled incorrectly");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error occurred during registration", e);
        }
    }

    @Override
    public User authorization(AuthInfo authInfo) throws ServiceException, ValidationException {
        try {
            if(validator.validateAuth(authInfo))
            {
                return userDAO.authUser(authInfo);
            }
            else {
                throw new ValidationException("The fields are filled incorrectly");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error occurred during authorization", e);
        }
    }

    @Override
    public User rememberMe(String login) throws ServiceException {
        try {
            return userDAO.rememberMe(login);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get tags", e);
        }
    }
}
