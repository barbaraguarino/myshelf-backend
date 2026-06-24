package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.application.dto.UserRegisterDTO;
import br.com.myshelf.backend.application.mapper.UserMapper;
import br.com.myshelf.backend.application.util.NicknameGenerator;
import br.com.myshelf.backend.domain.exception.BusinessRuleException;
import br.com.myshelf.backend.domain.model.User;
import br.com.myshelf.backend.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void createUser(UserRegisterDTO userRegisterDTO) {

        if(userRepository.existsByEmail(userRegisterDTO.email()))
            throw new BusinessRuleException("E-mail já existe.", "EMAIL_ALREADY_EXISTS");

        String encodedPassword = passwordEncoder.encode(userRegisterDTO.password());
        String nickname = NicknameGenerator.generate(userRegisterDTO.name());
        User user = userMapper.toEntity(userRegisterDTO, encodedPassword, nickname);
        userRepository.save(user);

    }

}
