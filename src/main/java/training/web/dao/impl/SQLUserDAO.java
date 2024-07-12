package training.web.dao.impl;

import training.web.bean.RegistrationInfo;
import training.web.dao.DAOException;
import training.web.dao.UserDAO;
import training.web.dao.connectionpool.ConnectionPool;
import training.web.dao.exception.ConnectionPoolException;
import training.web.dao.exception.EmailAlreadyExistsException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String USERS_ID_COLUMN = "id";

    private final static String INSERT_USER_SQL = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
    private final static String INSERT_ROLES_USERS_SQL = "INSERT INTO roles_users (users_id, roles_id) VALUES (?, ?)";
    @Override
    public boolean addUser(RegistrationInfo regInfo) throws DAOException, EmailAlreadyExistsException {
        try{
            Connection connection = connectionPool.takeConection();

            emailUniquenessCheck(regInfo.getEmail());

            PreparedStatement statementUsers = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            statementUsers.setString(1, regInfo.getLogin());
            statementUsers.setString(2, regInfo.getPassword());
            statementUsers.setString(3, regInfo.getEmail());

            if (statementUsers.executeUpdate() == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet keys = statementUsers.getGeneratedKeys();
            if(keys.next()){
                int userId = (int) keys.getInt(USERS_ID_COLUMN);
                userInfoInsert(userId, regInfo);
            }

            statementUsers.close();
            keys.close();

            PreparedStatement statementRolesUsers = connection.prepareStatement(INSERT_ROLES_USERS_SQL);
            System.out.println("Statement roles_users created");
            statementRolesUsers.setInt(1, userId);
            statementRolesUsers.setInt(2, 2); // Добавить нормальный выбор роли

            if (statementRolesUsers.executeUpdate() == 0) {
                throw new SQLException("Creating roles_users failed, no rows affected.");
            }
            statementRolesUsers.close();

            connection.close();
            } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException(e);
        } catch (ConnectionPoolException e){
            System.out.println(e.getMessage());
            throw new DAOException("Failed to open connection", e);
        }
        return false;
    }

    private final static String SELECT_USER_ID_SQL = "SELECT id FROM users WHERE email = ?";
    private boolean emailUniquenessCheck(String email) throws EmailAlreadyExistsException, SQLException, ConnectionPoolException{
        Connection connection = connectionPool.takeConection();
        PreparedStatement statementUserId = connection.prepareStatement(SELECT_USER_ID_SQL);
        statementUserId.setString(1, email);

        ResultSet resultSet = statementUserId.executeQuery();
        if(resultSet.next()){
            connectionPool.closeConnection(resultSet, statementUserId, connection);
            throw new EmailAlreadyExistsException("Email " + email + "already exists");
        }
        connectionPool.closeConnection(resultSet, statementUserId, connection);
        return true;
    }

    private final static String INSERT_USERINFO_SQL = "INSERT INTO userinfo (users_id, country, phone, name, surname, image) VALUES (?, ?, ?, ?, ?, ?);";
    public boolean userInfoInsert(int userId, RegistrationInfo regInfo) throws DAOException{
        Connection connection = null;
        PreparedStatement statementUserInfo = null;
        try{
            connection = connectionPool.takeConection();
            statementUserInfo = connection.prepareStatement(INSERT_USERINFO_SQL);
            System.out.println("Statement userInfo created");
            statementUserInfo.setInt(1, userId);
            statementUserInfo.setString(2, regInfo.getCountry());
            statementUserInfo.setString(3, regInfo.getPhone());
            statementUserInfo.setString(4, regInfo.getName());
            statementUserInfo.setString(5, regInfo.getSurname());
            statementUserInfo.setString(6, "");

            if (statementUserInfo.executeUpdate() == 0) {
                throw new SQLException("Creating userInfo failed, no rows affected.");
            }

            return true;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException("Error occurred during inserting user info.", e);
        } finally {
            connectionPool.closeConnection(statementUserInfo, connection);
        }
    }
}
