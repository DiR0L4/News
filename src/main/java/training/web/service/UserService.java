package training.web.service;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;

public interface UserService {
    boolean registration(RegistrationInfo regInfo) throws ServiceException;
    User authorization(AuthInfo authInfo) throws ServiceException;
}
