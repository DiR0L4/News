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
                System.out.println("Entered service");
                return userDAO.addUser(regInfo);
            }
            else {
                throw new ValidationException("The fields are filled in incorrectly");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error occurred during registration", e);
        }
    }

    @Override
    public User authorization(AuthInfo authInfo) throws ServiceException {
        return null;
    }
}
