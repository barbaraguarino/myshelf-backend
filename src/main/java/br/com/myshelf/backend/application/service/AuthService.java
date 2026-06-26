package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.application.dto.UserLoginDTO;
import br.com.myshelf.backend.application.dto.UserLoginResponseDTO;
import br.com.myshelf.backend.application.dto.UserRegisterDTO;
import br.com.myshelf.backend.application.mapper.UserMapper;
import br.com.myshelf.backend.application.util.NicknameGenerator;
import br.com.myshelf.backend.domain.exception.BusinessRuleException;
import br.com.myshelf.backend.domain.exception.ResourceAlreadyExistsException;
import br.com.myshelf.backend.domain.model.User;
import br.com.myshelf.backend.domain.repository.UserRepository;
import br.com.myshelf.backend.infrastructure.security.TokenService;
import br.com.myshelf.backend.infrastructure.security.UserDetailsAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public record AuthResultLogin(String token, UserLoginResponseDTO dto){}

    public void createUser(UserRegisterDTO userRegisterDTO) {

        if(userRepository.existsByEmail(userRegisterDTO.email()))
            throw new ResourceAlreadyExistsException("Usuário", "e-mail", userRegisterDTO.email());

        String encodedPassword = passwordEncoder.encode(userRegisterDTO.password());
        String nickname = NicknameGenerator.generate(userRegisterDTO.name());
        User user = userMapper.toEntity(userRegisterDTO, encodedPassword, nickname);
        userRepository.save(user);

    }

    public AuthResultLogin login(UserLoginDTO userLoginDTO){

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDTO.email(),
                userLoginDTO.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetailsAdapter userDetails = (UserDetailsAdapter) authentication.getPrincipal();
        assert userDetails != null;
        User user = userDetails.user();

        var token = tokenService.generateToken(user);
        var dto = userMapper.toUserLoginResponseDTO(user);

        return new AuthResultLogin(token, dto);

    }

}
