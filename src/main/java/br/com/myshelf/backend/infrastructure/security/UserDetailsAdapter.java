package br.com.myshelf.backend.infrastructure.security;

import br.com.myshelf.backend.domain.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetails {

    private final User user;

    @NonNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @NonNull
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    public User getUser() {
        return this.user;
    }

}
