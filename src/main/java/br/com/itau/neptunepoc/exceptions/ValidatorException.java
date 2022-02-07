package br.com.itau.neptunepoc.exceptions;


import java.util.Collection;

public class ValidatorException extends RuntimeException {

    private final Collection<Error> errors;

    public ValidatorException(Collection<Error> errors) {
        super();
        this.errors = errors;
    }

    public Collection<Error> getErrors() {
        return errors;
    }
}
