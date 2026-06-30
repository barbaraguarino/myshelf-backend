package br.com.myshelf.backend.core.exception.domain;

public class ResourceAlreadyExistsException extends DomainException {

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object value) {
        super( String.format("%s com %s '%s' já está cadastrado no sistema.", resourceName, fieldName, value), "RESOURCE_ALREADY_EXISTS");
    }
}
