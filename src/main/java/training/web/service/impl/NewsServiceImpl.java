package training.web.service.impl;

import training.web.bean.News;
import training.web.dao.DAOException;
import training.web.dao.DAOProvider;
import training.web.dao.NewsDAO;
import training.web.service.NewsService;
import training.web.service.ServiceException;
import training.web.service.exception.ValidationException;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
    @Override
    public List<News> getLastNews() throws ServiceException{
        try {
            return newsDAO.getLastNews();
        } catch (DAOException e) {
            throw new ServiceException("An error occurred while trying to get the latest news", e);
        }
    }
}
