package br.com.myshelf.backend.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final String errorCode;

    public DomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
