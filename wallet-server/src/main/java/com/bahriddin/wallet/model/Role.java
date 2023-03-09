package com.bahriddin.wallet.model;

import com.bahriddin.wallet.enums.RoleType;
import com.bahriddin.wallet.model.template.AbstractUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractUUIDEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType role;
    private boolean active;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
