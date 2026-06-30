package br.com.myshelf.backend.core.exception.domain;

public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(String.format("%s com identificador '%s' não foi encontrado.", resourceName, identifier), "RESOURCE_NOT_FOUND");
    }

}
