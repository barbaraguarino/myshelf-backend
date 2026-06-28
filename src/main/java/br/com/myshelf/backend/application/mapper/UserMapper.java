package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.auth.UserLoginResponseDTO;
import br.com.myshelf.backend.application.dto.auth.UserRegisterDTO;
import br.com.myshelf.backend.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRegisterDTO userRegisterDTO, String encodedPassword, String nickname) {
        if(userRegisterDTO == null)
            return null;

        return User.createUser(userRegisterDTO.name(),
                nickname,
                userRegisterDTO.email(),
                encodedPassword);
    }

    public UserLoginResponseDTO toResponseDTO(User user){
        if(user == null)
            return null;

        return new UserLoginResponseDTO(user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getCreatedAt());
    }
}
