package training.web.dao.impl;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.bean.UserProfile;
import training.web.dao.exception.DAOException;
import training.web.dao.UserDAO;
import training.web.dao.connectionpool.ConnectionPool;
import training.web.dao.exception.ConnectionPoolException;
import training.web.dao.exception.EmailAlreadyExistsDAOException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String USERS_ID_COLUMN = "id";
    private final static String USERS_LOGIN_COLUMN = "login";
    private final static String USERS_EMAIL_COLUMN = "email";
    private final static String ROLES_USERS_ROLES_ID_COLUMN = "roles_id";
    private final static String USERSINFO_SURNAME_COLUMN = "surname";
    private final static String USERSINFO_NAME_COLUMN = "surname";
    private final static String ROLES_TITLE_COLUMN = "title";
    private final static String USERSINFO_COUNTRY_COLUMN = "country";

    private final static String INSERT_USER_SQL = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";

    @Override
    public boolean addUser(RegistrationInfo regInfo) throws DAOException, EmailAlreadyExistsDAOException {
        Connection connection = null;
        PreparedStatement statementUsers = null;
        ResultSet keys = null;
        try{
            connection = connectionPool.takeConnection();

            emailUniquenessCheck(regInfo.getEmail());

            statementUsers = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            statementUsers.setString(1, regInfo.getLogin());
            statementUsers.setString(2, regInfo.getPassword());
            statementUsers.setString(3, regInfo.getEmail());

            if (statementUsers.executeUpdate() == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            keys = statementUsers.getGeneratedKeys();
            if(keys.next()){
                int userId = keys.getInt(USERS_ID_COLUMN);
                connectionPool.closeConnection(keys, statementUsers, connection);
                userInfoInsert(userId, regInfo);
                rolesUsersInsert(userId, regInfo);
            }

            return true;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(keys, statementUsers, connection);
        }
    }

    private final static String SELECT_USER_ID_SQL = "SELECT id FROM users WHERE email = ?";
    private boolean emailUniquenessCheck(String email) throws EmailAlreadyExistsDAOException, SQLException, ConnectionPoolException{
        Connection connection = connectionPool.takeConnection();
        PreparedStatement statementUserId = connection.prepareStatement(SELECT_USER_ID_SQL);
        statementUserId.setString(1, email);

        ResultSet resultSet = statementUserId.executeQuery();
        if(resultSet.next()){
            connectionPool.closeConnection(resultSet, statementUserId, connection);
            throw new EmailAlreadyExistsDAOException("Email " + email + "already exists");
        }
        connectionPool.closeConnection(resultSet, statementUserId, connection);
        return true;
    }

    private final static String INSERT_USERINFO_SQL = "INSERT INTO userinfo (users_id, country, phone, name, surname, image) VALUES (?, ?, ?, ?, ?, ?);";
    public boolean userInfoInsert(int userId, RegistrationInfo regInfo) throws DAOException{
        Connection connection = null;
        PreparedStatement statementUserInfo = null;
        try{
            connection = connectionPool.takeConnection();
            statementUserInfo = connection.prepareStatement(INSERT_USERINFO_SQL);
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
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(statementUserInfo, connection);
        }
    }

    private final static String INSERT_ROLES_USERS_SQL = "INSERT INTO roles_users (users_id, roles_id) VALUES (?, ?)";
    private boolean rolesUsersInsert(int userId, RegistrationInfo regInfo) throws DAOException{
        Connection connection = null;
        PreparedStatement statementRolesUsers = null;
        try{
            connection = connectionPool.takeConnection();
            statementRolesUsers = connection.prepareStatement(INSERT_ROLES_USERS_SQL);
            statementRolesUsers.setInt(1, userId);
            statementRolesUsers.setInt(2, regInfo.getRoleId());

            if (statementRolesUsers.executeUpdate() == 0) {
                throw new SQLException("Creating roles_users failed, no rows affected.");
            }

            return true;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(statementRolesUsers, connection);
        }
    }

    private final static String SELECT_AUTH_SQL = "SELECT id, login, email, roles_id FROM users " +
            "JOIN roles_users ON roles_users.users_id = users.id " +
            "WHERE users.email = ? AND users.password = ?";
    @Override
    public User authUser(AuthInfo authInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(SELECT_AUTH_SQL);
            statement.setString(1, authInfo.getEmail());
            statement.setString(2, authInfo.getPassword());

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(USERS_ID_COLUMN);
                String login = resultSet.getString(USERS_LOGIN_COLUMN);
                String email = resultSet.getString(USERS_EMAIL_COLUMN);
                int roleId = resultSet.getInt(ROLES_USERS_ROLES_ID_COLUMN);

                connectionPool.closeConnection(resultSet, statement, connection);

                return new User(userId, login, email, roleId, "");
            }

            return null;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(resultSet, statement, connection);
        }
    }

    private final static String SELECT_REMEMBER_ME_SQL = "SELECT id, login, email, roles_id FROM users " +
            "JOIN roles_users ON roles_users.users_id = users.id " +
            "WHERE users.login = ?";
    @Override
    public User rememberMe(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(SELECT_REMEMBER_ME_SQL);
            statement.setString(1, login);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(USERS_ID_COLUMN);
                String email = resultSet.getString(USERS_EMAIL_COLUMN);
                int roleId = resultSet.getInt(ROLES_USERS_ROLES_ID_COLUMN);

                connectionPool.closeConnection(resultSet, statement, connection);

                return new User(userId, login, email, roleId, "");
            }

            return null;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(resultSet, statement, connection);
        }
    }

    private final static String SELECT_PROFILE_SQL = "SELECT login, email, roles_id, name, surname, country, roles.title FROM users\n" +
            "JOIN roles_users ON roles_users.users_id = users.id\n" +
            "JOIN roles ON roles.id = roles_users.users_id\n" +
            "JOIN userinfo ON userinfo.users_id = users.id\n" +
            "WHERE users.id = ?";

    @Override
    public UserProfile getUserProfile(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(SELECT_PROFILE_SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString(USERS_LOGIN_COLUMN);
                String surname = resultSet.getString(USERSINFO_SURNAME_COLUMN);
                String name = resultSet.getString(USERSINFO_NAME_COLUMN);
                String email = resultSet.getString(USERS_EMAIL_COLUMN);
                String role = resultSet.getString(ROLES_TITLE_COLUMN);
                String country = resultSet.getString(USERSINFO_COUNTRY_COLUMN);

                return new UserProfile(login, name, email, surname, country, role);
            }

            return null;
        } catch (ConnectionPoolException | SQLException e){
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(resultSet, statement, connection);
        }
    }
}
