package training.web.service.exception;

public class EmailAlreadyExistsServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsServiceException(String message) {
        super(message);
    }
    public EmailAlreadyExistsServiceException(Exception e) {
        super(e);
    }
}
