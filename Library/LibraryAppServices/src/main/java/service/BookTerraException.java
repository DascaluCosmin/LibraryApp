package service;

public class BookTerraException extends Exception {

    public BookTerraException() {
    }

    public BookTerraException(String message) {
        super(message);
    }

    public BookTerraException(String message, Throwable cause) {
        super(message, cause);
    }
}
