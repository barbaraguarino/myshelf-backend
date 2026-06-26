package br.com.myshelf.backend.infrastructure.web;

import br.com.myshelf.backend.infrastructure.web.dto.FieldErrorResponse;
import br.com.myshelf.backend.domain.exception.BusinessRuleException;
import br.com.myshelf.backend.domain.exception.DomainException;
import br.com.myshelf.backend.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildDomainResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex, request);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ProblemDetail> handleBusinessRule(BusinessRuleException ex, HttpServletRequest request) {
        return buildDomainResponse(HttpStatus.UNPROCESSABLE_CONTENT, "Unprocessable Content", ex, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleGenericDomainException(DomainException ex, HttpServletRequest request) {
        logger.warn("DomainException sem tratamento específico interceptada: {}", ex.getMessage());
        return buildDomainResponse(HttpStatus.BAD_REQUEST, "Domain Error", ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldErrorResponse> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new FieldErrorResponse(f.getField(), f.getCode(), f.getDefaultMessage()))
                .toList();

        ProblemDetail problemDetail = createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Validation Error",
                "Falha na validação de um ou mais campos da requisição.",
                "VALIDATION_ERROR",
                request.getRequestURI()
        );

        problemDetail.setProperty("validationErrors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllUncaughtException(Exception ex, HttpServletRequest request) {
        logger.error("Erro catastrófico interceptado na rota [{}]: ", request.getRequestURI(), ex);

        ProblemDetail problemDetail = createProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "Ocorreu um erro interno inesperado no servidor. Contate o suporte.",
                "INTERNAL_ERROR",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentials(HttpServletRequest request) {
        ProblemDetail problemDetail = createProblemDetail(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                "E-mail ou senha incorretos.",
                "BAD_CREDENTIALS",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    private ResponseEntity<ProblemDetail> buildDomainResponse(HttpStatus status, String title, DomainException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = createProblemDetail(
                status,
                title,
                ex.getMessage(),
                ex.getErrorCode(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(problemDetail);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail, String errorCode, String uri) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(uri));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCode", errorCode);

        return problemDetail;
    }
}