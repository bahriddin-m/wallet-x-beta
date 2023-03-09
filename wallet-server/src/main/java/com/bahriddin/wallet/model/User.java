package com.bahriddin.wallet.model;

import com.bahriddin.wallet.model.template.AbstractLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.bahriddin.wallet.utils.constants.Constants.NAME_LENGTH;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractLongEntity implements UserDetails {
    @Column(nullable = false, length = NAME_LENGTH)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @OneToOne
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
