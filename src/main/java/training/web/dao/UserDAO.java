package training.web.dao;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.bean.UserProfile;
import training.web.dao.exception.DAOException;
import training.web.dao.exception.EmailAlreadyExistsDAOException;

public interface UserDAO {
    boolean addUser(RegistrationInfo regInfo) throws DAOException, EmailAlreadyExistsDAOException;
    User authUser(AuthInfo authInfo) throws DAOException;
    User rememberMe(String login) throws DAOException;
    UserProfile getUserProfile(int id) throws DAOException;
}
