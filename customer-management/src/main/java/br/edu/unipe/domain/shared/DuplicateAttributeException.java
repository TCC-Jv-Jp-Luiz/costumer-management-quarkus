package br.edu.unipe.domain.shared;

public class DuplicateAttributeException extends BusinessException {
    public DuplicateAttributeException(String attribute) {
        super(attribute + " already registered.");
    }
}