package by.chebotar.controller.command;

/**
 * Provide route to jsp page
 */
public class Router {
    private String route;
    private Type type = Type.FORWARD;

    public enum Type {
        FORWARD, REDIRECT
    }

}
