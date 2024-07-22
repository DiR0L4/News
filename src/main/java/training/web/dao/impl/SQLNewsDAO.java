package training.web.dao.impl;

import training.web.bean.News;
import training.web.bean.Tag;
import training.web.dao.DAOException;
import training.web.dao.NewsDAO;
import training.web.dao.connectionpool.ConnectionPool;
import training.web.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLNewsDAO implements NewsDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String NEWS_ID_COLUMN = "id";
    private final static String NEWS_TITLE_COLUMN = "title";
    private final static String NEWS_BRIEF_COLUMN = "brief";
    private final static String NEWS_INFO_COLUMN = "info";
    private final static String NEWS_IMAGE_COLUMN = "image";

    private final static String TAGS_ID_COLUMN = "id";
    private final static String TAGS_TITLE_COLUMN = "title";

    private final static String SELECT_LAST_NEWS_SQL = "SELECT * FROM news ORDER BY id DESC LIMIT 5";
    @Override
    public List<News> getLastNews() throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<News> list = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SELECT_LAST_NEWS_SQL);
            resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                News news = new News();

                news.setId(resultSet.getInt(NEWS_ID_COLUMN));
                news.setTitle(resultSet.getString(NEWS_TITLE_COLUMN));
                news.setBrief(resultSet.getString(NEWS_BRIEF_COLUMN));
                news.setInfo(resultSet.getString(NEWS_INFO_COLUMN));
                news.setImgPath(resultSet.getString(NEWS_IMAGE_COLUMN));

                list.add(news);
            }

            return list;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(resultSet, statement, connection);
        }
    }

    private final static String INSERT_NEWS_SQL = "INSERT INTO public.news(title, brief, info, image, tag_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean addNews(News news) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(INSERT_NEWS_SQL);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBrief());
            statement.setString(3, news.getInfo());
            statement.setString(4, news.getImgPath());
            statement.setInt(5, news.getTagId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Creating news failed, no rows affected.");
            }

            return true;

        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(statement, connection);
        }
    }

    private final static String SELECT_TAGS_SQL = "SELECT * FROM tags";

    @Override
    public List<Tag> getTags() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Tag> list = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SELECT_TAGS_SQL);
            resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Tag tag = new Tag();

                tag.setId(resultSet.getInt(TAGS_ID_COLUMN));
                tag.setTitle(resultSet.getString(TAGS_TITLE_COLUMN));

                list.add(tag);
            }

            return list;
        } catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(resultSet, statement, connection);
        }
    }
}
