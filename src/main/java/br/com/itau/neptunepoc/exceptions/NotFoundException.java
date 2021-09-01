package br.com.itau.neptunepoc.exceptions;

public class NotFoundException extends RuntimeException {

    private final String resource;
    private final transient Object value;

    public <T> NotFoundException(Class<T> resource, Object value) {
        this.resource = resource.getSimpleName();
        this.value = value;
    }

    @Override
    public String getMessage() {
        return String.format("Não foi possível encontrar o recurso [%s] com o valor [%s]", resource, value);
    }
}
