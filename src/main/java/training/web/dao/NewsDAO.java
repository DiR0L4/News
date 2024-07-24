package training.web.dao;

import training.web.bean.News;
import training.web.bean.Tag;

import java.util.List;

public interface NewsDAO {
    List<News> getLastNews() throws DAOException;
    boolean addNews(News news) throws DAOException;
    List<Tag> getTags() throws DAOException;
    News getNewsById(int id) throws DAOException;
    boolean updateNews(News news) throws DAOException;
    boolean deleteNews(int id) throws DAOException;
    List<News> getNewsByTagId(int tagId) throws DAOException;
}
