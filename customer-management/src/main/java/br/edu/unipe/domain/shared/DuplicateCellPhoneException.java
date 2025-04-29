package br.edu.unipe.domain.shared;

public class DuplicateCellPhoneException extends BusinessException {
    public DuplicateCellPhoneException() {
        super("CellPhone already registered.");
    }
}