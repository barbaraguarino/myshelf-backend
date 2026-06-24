package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.UserRegisterDTO;
import br.com.myshelf.backend.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        authService.createUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
