package training.web.service;

import training.web.bean.News;
import training.web.bean.Tag;
import training.web.dao.DAOException;
import training.web.service.exception.ValidationException;

import java.util.List;

public interface NewsService {
    List<News> getLastNews() throws ServiceException;
    List<Tag> getTags() throws ServiceException;
    boolean addNews(News news) throws ServiceException;
    News getNewsById(int id) throws ServiceException;
    boolean updateNews(News news) throws ServiceException;
    boolean deleteNews(int id) throws ServiceException;
    List<News> getNewsByTagId(int tagId) throws ServiceException;
}
