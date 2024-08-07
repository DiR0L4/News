package training.web.dao.connectionpool;

import training.web.dao.connectionpool.parameter.DBParameter;
import training.web.dao.connectionpool.parameter.DBResourceManager;
import training.web.dao.exception.ConnectionPoolException;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConnectionPool {

    private static final ConnectionPool instance = new ConnectionPool();
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());

    public static ConnectionPool getInstance() {
        return instance;
    }

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private final String driverName;
    private final String url;
    private final String login;
    private final String password;
    private final int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.login = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        try {
            initPoolData();
            LOGGER.log(Level.INFO, "Connection pool initialized successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DB driver not found");
            throw new RuntimeException("DB driver not found", e);
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Error while initializing connection");
            throw new RuntimeException(e);
        }
    }

    private void initPoolData() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);

        connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
        givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, login, password);
            PooledConnection pooledConnection = new PooledConnection(connection);
            connectionQueue.add(pooledConnection);
            LOGGER.log(Level.INFO, "New connection added: " + pooledConnection);
        }
    }

    // clean
    public void dispose() {
        try{
            clearConnectionQueue();
            DriverManager.deregisterDriver(DriverManager.getDriver(driverName));
            LOGGER.log(Level.INFO, "Driver removed:" + driverName);
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB access error");
        }
    }

    private void clearConnectionQueue() throws SQLException{
        closeConnestionsQueue(givenAwayConQueue);
        closeConnestionsQueue(connectionQueue);
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
            LOGGER.log(Level.INFO, "Connection obtained");
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while trying to obtain a connection", e);
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    public void closeConnection(ResultSet rs, PreparedStatement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing result set", e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing statement", e);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing connection", e);
        }
    }

    public void closeConnection(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing statement", e);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing connection", e);
        }
    }

    private void closeConnestionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;

        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    // Pattern decorator
    private class PooledConnection implements Connection {

        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {

            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("Connection already closed");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenAwayConQueue.remove(this)) {
                throw new SQLException("An error occurred while removing the connection");
            }
            if (!connectionQueue.offer(this)) {
                throw new SQLException("Connection does not exist");
            }
            LOGGER.log(Level.SEVERE, "Connection closed");
        }

        public void commit() throws SQLException {
            connection.commit();
        }

        public boolean isWrapperFor(Class<?> arg0) throws SQLException {
            return connection.isWrapperFor(arg0);
        }

        public <T> T unwrap(Class<T> arg0) throws SQLException {
            return connection.unwrap(arg0);
        }

        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        public void rollback() throws SQLException {
            connection.rollback();
        }

        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

    }
}
