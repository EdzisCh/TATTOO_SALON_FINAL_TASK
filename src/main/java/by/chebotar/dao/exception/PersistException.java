package by.chebotar.dao.exception;

public class PersistException extends Exception {

    public PersistException(String message, Throwable cause){
        super(message, cause);
    }

    public PersistException(String message){
        super(message);
    }
}
