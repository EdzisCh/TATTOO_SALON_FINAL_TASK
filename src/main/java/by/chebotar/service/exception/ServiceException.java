package by.chebotar.service.exception;

/**
 * Service Exception
 */
public class ServiceException extends Exception {


    public ServiceException(){}

    public ServiceException(Throwable cause){
        super(cause);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
