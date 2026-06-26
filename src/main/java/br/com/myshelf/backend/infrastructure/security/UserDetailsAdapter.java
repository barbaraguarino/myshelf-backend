package br.com.myshelf.backend.infrastructure.security;

import br.com.myshelf.backend.domain.model.User;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record UserDetailsAdapter(User user) implements UserDetails {

    @NonNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.user.getPassword();
    }

    @NonNull
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

}
