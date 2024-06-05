package training.web.dao;

import training.web.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider INSTANCE = new DAOProvider();

    private UserDAO userDao = new SQLUserDAO();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDao() {
        return userDao;
    }
}
