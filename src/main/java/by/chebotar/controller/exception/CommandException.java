package by.chebotar.controller.exception;

public class CommandException extends RuntimeException{

    public CommandException(Throwable cause){
        super(cause);
    }

    public CommandException(String msg, Throwable cause){
        super(msg, cause);
    }
}
