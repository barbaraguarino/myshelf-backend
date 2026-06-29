package br.com.myshelf.backend.core.exception.domain;

public class BusinessRuleException extends DomainException {

    public BusinessRuleException(String message) {
        super(message, "BUSINESS_RULE_VIOLATION");
    }

    public BusinessRuleException(String message, String errorCode) {
        super(message, errorCode);
    }

}
