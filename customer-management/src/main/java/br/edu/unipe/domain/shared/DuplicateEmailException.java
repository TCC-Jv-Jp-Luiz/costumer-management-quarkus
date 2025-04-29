package br.edu.unipe.domain.shared;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super("Email already registered.");
    }
}