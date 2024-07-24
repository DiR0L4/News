package training.web.service;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.bean.UserProfile;
import training.web.dao.exception.DAOException;
import training.web.service.exception.EmailAlreadyExistsServiceException;
import training.web.service.exception.ServiceException;
import training.web.service.exception.ValidationException;

public interface UserService {
    boolean registration(RegistrationInfo regInfo) throws ServiceException, EmailAlreadyExistsServiceException, ValidationException;
    User authorization(AuthInfo authInfo) throws ServiceException, ValidationException;
    User rememberMe(String login) throws ServiceException;
    UserProfile getUserProfile(int id) throws ServiceException;
}
