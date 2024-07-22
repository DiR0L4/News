package training.web.dao;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.dao.exception.EmailAlreadyExistsException;

public interface UserDAO {
    boolean addUser(RegistrationInfo regInfo) throws DAOException, EmailAlreadyExistsException;
    User authUser(AuthInfo authInfo) throws DAOException;
    User rememberMe(String login) throws DAOException;
}
