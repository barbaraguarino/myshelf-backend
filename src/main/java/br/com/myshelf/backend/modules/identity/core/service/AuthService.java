package br.com.myshelf.backend.modules.identity.core.service;

import br.com.myshelf.backend.core.util.NicknameGenerator;
import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.core.security.TokenService;
import br.com.myshelf.backend.core.security.UserDetailsAdapter;
import br.com.myshelf.backend.modules.identity.api.dto.UserLoginDTO;
import br.com.myshelf.backend.modules.identity.api.dto.UserLoginResponseDTO;
import br.com.myshelf.backend.modules.identity.api.dto.UserRegisterDTO;
import br.com.myshelf.backend.modules.identity.api.mapper.UserMapper;
import br.com.myshelf.backend.modules.identity.core.model.User;
import br.com.myshelf.backend.modules.identity.data.UserRepository;
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
        UserLoginResponseDTO dto = userMapper.toResponseDTO(user);

        return new AuthResultLogin(token, dto);

    }

}
