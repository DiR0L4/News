package training.web.service.impl;

import training.web.bean.News;
import training.web.bean.Tag;
import training.web.dao.DAOException;
import training.web.dao.DAOProvider;
import training.web.dao.NewsDAO;
import training.web.service.NewsService;
import training.web.service.ServiceException;
import training.web.service.exception.ValidationException;
import training.web.service.util.Validator;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
    Validator validator = new Validator();
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
            throw new ServiceException("An error occurred while trying to get tags", e);
        }
    }
}
