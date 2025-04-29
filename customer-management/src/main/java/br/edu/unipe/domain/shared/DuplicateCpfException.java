package br.edu.unipe.domain.shared;

public class DuplicateCpfException extends BusinessException {
    public DuplicateCpfException() {
        super("CPF already registered.");
    }
}
