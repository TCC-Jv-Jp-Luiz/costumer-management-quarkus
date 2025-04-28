package br.edu.unipe.domain.shared;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}