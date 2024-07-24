package training.web.service.impl;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.dao.DAOException;
import training.web.dao.DAOProvider;
import training.web.dao.UserDAO;
import training.web.dao.exception.EmailAlreadyExistsDAOException;
import training.web.service.ServiceException;
import training.web.service.UserService;
import training.web.service.exception.EmailAlreadyExistsServiceException;
import training.web.service.exception.ValidationException;
import training.web.service.util.Validator;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDao();
    private final Validator validator = new Validator();
    @Override
    public boolean registration(RegistrationInfo regInfo) throws ServiceException, EmailAlreadyExistsServiceException, ValidationException {
        try {
            if(validator.validateRegistration(regInfo))
            {
                return userDAO.addUser(regInfo);
            }
            else {
                throw new ValidationException("The fields are filled incorrectly");
            }
        } catch (EmailAlreadyExistsDAOException e){
            throw new EmailAlreadyExistsServiceException(e);
        }
        catch (DAOException e) {
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
            throw new ServiceException("An error occurred while trying to get token", e);
        }
    }
}
