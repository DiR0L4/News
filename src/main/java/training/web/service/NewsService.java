package training.web.service;

import training.web.bean.News;
import training.web.bean.Tag;
import training.web.service.exception.ValidationException;

import java.util.List;

public interface NewsService {
    List<News> getLastNews() throws ServiceException;
    List<Tag> getTags() throws ServiceException;
    boolean addNews(News news) throws ServiceException;
}
