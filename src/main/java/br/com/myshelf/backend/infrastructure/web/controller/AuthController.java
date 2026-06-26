package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.UserLoginDTO;
import br.com.myshelf.backend.application.dto.UserLoginResponseDTO;
import br.com.myshelf.backend.application.dto.UserRegisterDTO;
import br.com.myshelf.backend.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthController {

    private final AuthService authService;

    @Value("${api.security.token.name}")
    private String TOKEN_NAME;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        authService.createUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {

        var response = authService.login(userLoginDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, getResponseCookie(response.token()).toString())
                .body(response.dto());

    }

    private ResponseCookie getResponseCookie(String token) {
        return ResponseCookie.from(TOKEN_NAME, token)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(86400)
                .build();
    }
}
