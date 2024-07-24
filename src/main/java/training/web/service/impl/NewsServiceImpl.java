package training.web.service.impl;

import training.web.bean.News;
import training.web.bean.Tag;
import training.web.dao.exception.DAOException;
import training.web.dao.DAOProvider;
import training.web.dao.NewsDAO;
import training.web.service.NewsService;
import training.web.service.exception.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
    @Override
    public List<News> getLastNews() throws ServiceException{
        try {
            return newsDAO.getLastNews();
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get the latest news", e);
        }
    }

    @Override
    public List<Tag> getTags() throws ServiceException {
        try {
            return newsDAO.getTags();
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get tags", e);
        }
    }

    @Override
    public boolean addNews(News news) throws ServiceException {
        try {
            return newsDAO.addNews(news);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get news", e);
        }
    }

    @Override
    public News getNewsById(int id) throws ServiceException {
        try {
            return newsDAO.getNewsById(id);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get news", e);
        }
    }

    @Override
    public boolean updateNews(News news) throws ServiceException {
        try {
            return newsDAO.updateNews(news);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to update news", e);
        }
    }

    @Override
    public boolean deleteNews(int id) throws ServiceException {
        try {
            return newsDAO.deleteNews(id);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to delete news", e);
        }
    }

    @Override
    public List<News> getNewsByTagId(int tagId) throws ServiceException {
        try {
            return newsDAO.getNewsByTagId(tagId);
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get news by tag", e);
        }
    }
}
