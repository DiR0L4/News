package training.web.dao;

import training.web.bean.News;

import java.util.List;

public interface NewsDAO {
    List<News> getLastNews() throws DAOException;
}
