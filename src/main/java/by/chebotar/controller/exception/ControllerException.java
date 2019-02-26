package by.chebotar.controller.exception;

public class ControllerException extends RuntimeException{

    public ControllerException(){}

    public ControllerException(String msg, Throwable cause){
        super(msg, cause);
    }
}
