package training.web.service;

import training.web.bean.News;

import java.util.List;

public interface NewsService {
    List<News> getLastNews() throws ServiceException;
}
