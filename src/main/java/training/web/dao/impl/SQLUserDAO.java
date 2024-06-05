package training.web.dao.impl;

import training.web.bean.RegistrationInfo;
import training.web.dao.DAOException;
import training.web.dao.DBParameter;
import training.web.dao.DBResourceManager;
import training.web.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    /*private DBResourceManager dbResourceManager = DBResourceManager.getInstance();
    private String url = dbResourceManager.getValue(DBParameter.DB_URL);
    private String user = dbResourceManager.getValue(DBParameter.DB_USER);
    private String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);*/
    @Override
    public boolean addUser(RegistrationInfo regInfo) throws DAOException {
        /*try {
            Connection connection = DriverManager.getConnection(url, user, password);
            }catch (SQLException e) {
            throw new DAOException("Failed to open connection", e);
        }*/
        return false;
    }
}
