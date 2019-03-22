package by.chebotar.command.exception;

public class ControllerException extends RuntimeException{

    public ControllerException(){}

    public ControllerException(String msg, Throwable cause){
        super(msg, cause);
    }
}
