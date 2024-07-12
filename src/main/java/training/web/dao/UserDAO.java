package training.web.dao;

import training.web.bean.RegistrationInfo;
import training.web.dao.exception.EmailAlreadyExistsException;

public interface UserDAO {
    boolean addUser(RegistrationInfo regInfo) throws DAOException, EmailAlreadyExistsException;
}
