package pl.sztukakodu.TaskTreeApp.tasks.exceptions;

public class NotFoundExcpetion extends RuntimeException {
    public NotFoundExcpetion(String message) {
        super(message);
    }

    public NotFoundExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
