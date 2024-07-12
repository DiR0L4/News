package training.web.service.impl;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;
import training.web.bean.User;
import training.web.dao.DAOProvider;
import training.web.dao.UserDAO;
import training.web.service.ServiceException;
import training.web.service.UserService;
import training.web.service.util.Validator;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = DAOProvider.getInstance().getUserDao();
    Validator validator = new Validator();
    @Override
    public boolean registration(RegistrationInfo regInfo) throws ServiceException {
        try {
            // Вызов записи в бд в DAO
            if(validator.validateRegistration(regInfo))
            {
                return userDAO.addUser(regInfo);
            }
            else {
                throw new ServiceException("The fields are filled in incorrectly");
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User authorization(AuthInfo authInfo) throws ServiceException {
        return null;
    }
}
