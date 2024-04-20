package br.com.academy.Exceptions;

public class CriptoExistsException extends Throwable {
    public CriptoExistsException(String message) {
        super(message);
    }
    private static final long serialVersionUID = 1L;
}
