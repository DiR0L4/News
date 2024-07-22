package training.web.service;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.dao.DAOException;
import training.web.dao.exception.EmailAlreadyExistsException;
import training.web.service.exception.ValidationException;

public interface UserService {
    boolean registration(RegistrationInfo regInfo) throws ServiceException, EmailAlreadyExistsException, ValidationException;
    User authorization(AuthInfo authInfo) throws ServiceException, ValidationException;
    User rememberMe(String login) throws ServiceException;
}
