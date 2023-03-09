package com.bahriddin.wallet.component;

import com.bahriddin.wallet.enums.RoleType;
import com.bahriddin.wallet.model.Role;
import com.bahriddin.wallet.model.User;
import com.bahriddin.wallet.repository.RoleRepository;
import com.bahriddin.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
//            var user = User
//                    .builder()
//                    .fullName("Admin")
//                    .email("admin@gmail.com")
//                    .password(passwordEncoder.encode("admin"))
//                    .role(roleRepository.findByRole(RoleType.ADMIN))
//                    .build();
//
//            userRepository.save(user);
            ArrayList<Role> roles = new ArrayList<>();
            Role roleUser = new Role(
                    RoleType.USER, true
            );
            Role roleProUser = new Role(
                    RoleType.PRO_USER, true
            );
            Role roleAdmin = new Role(
                    RoleType.ADMIN, true
            );
            roles.add(roleUser);
            roles.add(roleProUser);
            roles.add(roleAdmin);
            roleRepository.saveAll(roles);
        }
    }
}
