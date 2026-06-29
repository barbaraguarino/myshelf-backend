package br.com.myshelf.backend.modules.identity.api.mapper;

import br.com.myshelf.backend.modules.identity.api.dto.UserLoginResponseDTO;
import br.com.myshelf.backend.modules.identity.api.dto.UserRegisterDTO;
import br.com.myshelf.backend.modules.identity.core.model.User;
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
