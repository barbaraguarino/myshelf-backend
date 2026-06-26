package br.com.myshelf.backend.domain.exception;

public class ResourceAlreadyExistsException extends DomainException {

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object value) {
        super( String.format("%s com %s '%s' já está cadastrado no sistema.", resourceName, fieldName, value), "RESOURCE_ALREADY_EXISTS");
    }
}
