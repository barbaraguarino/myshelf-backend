package br.com.myshelf.backend.infrastructure.web;

import br.com.myshelf.backend.application.dto.FieldErrorResponse;
import br.com.myshelf.backend.application.dto.StandardErrorResponse;
import br.com.myshelf.backend.domain.exception.BusinessRuleException;
import br.com.myshelf.backend.domain.exception.DomainException;
import br.com.myshelf.backend.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildDomainResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex, request);
    }


    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardErrorResponse> handleBusinessRule(BusinessRuleException ex, HttpServletRequest request) {
        return buildDomainResponse(HttpStatus.UNPROCESSABLE_CONTENT, "Unprocessable Entity", ex, request);
    }


    @ExceptionHandler(DomainException.class)
    public ResponseEntity<StandardErrorResponse> handleGenericDomainException(DomainException ex, HttpServletRequest request) {
        logger.warn("DomainException tratada pelo fallback genérico. Verifique se falta um handler específico: {}", ex.getMessage());
        return buildDomainResponse(HttpStatus.BAD_REQUEST, "Domain Error", ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<FieldErrorResponse> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new FieldErrorResponse(f.getField(), f.getDefaultMessage()))
                .toList();

        StandardErrorResponse error = new StandardErrorResponse(
                Instant.now(),
                status.value(),
                "VALIDATION_ERROR",
                "Bad Request",
                "Falha na validação de um ou mais campos da requisição.",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> handleAllUncaughtException(Exception ex, HttpServletRequest request) {
        logger.error("Erro interno inesperado detectado no caminho: {}", request.getRequestURI(), ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardErrorResponse error = new StandardErrorResponse(
                Instant.now(),
                status.value(),
                "INTERNAL_ERROR",
                "Internal Server Error",
                "Ocorreu um erro interno inesperado no servidor. Contate o suporte.",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(status).body(error);
    }

    private ResponseEntity<StandardErrorResponse> buildDomainResponse(
            HttpStatus status,
            String errorType,
            DomainException ex,
            HttpServletRequest request) {

        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(),
                status.value(),
                ex.getErrorCode(),
                errorType,
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(status).body(response);
    }
}
