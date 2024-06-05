package training.web.dao;

import training.web.bean.RegistrationInfo;

public interface UserDAO {
    boolean addUser(RegistrationInfo regInfo) throws DAOException;
}
