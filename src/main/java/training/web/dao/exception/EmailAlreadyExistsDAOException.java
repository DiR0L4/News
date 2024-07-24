package training.web.dao.exception;

import training.web.dao.DAOException;

public class EmailAlreadyExistsDAOException extends DAOException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsDAOException(String message) {
        super(message);
    }
    public EmailAlreadyExistsDAOException(Exception e) {
        super(e);
    }
}
