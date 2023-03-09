package com.bahriddin.wallet.auth;

import com.bahriddin.wallet.enums.RoleType;
import com.bahriddin.wallet.model.User;
import com.bahriddin.wallet.payload.response.ApiResponse;
import com.bahriddin.wallet.repository.RoleRepository;
import com.bahriddin.wallet.repository.TokenRepository;
import com.bahriddin.wallet.repository.UserRepository;
import com.bahriddin.wallet.security.jwt.JwtService;
import com.bahriddin.wallet.model.Token;
import com.bahriddin.wallet.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse<?> signup(RegisterRequest request) {
        boolean userAlreadyExist = userRepository.existsByEmail(request.getEmail());
        if (userAlreadyExist) return new ApiResponse(false, "User already exist: " + request.getEmail());

        var user = User
                .builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByRole(RoleType.USER))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return new ApiResponse<>(
                true,
                AuthenticationResponse
                        .builder()
                        .token(jwtToken)
                        .build()
        );
    }

    public ApiResponse<?> login(AuthenticationRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //Todo need to check not found logic
   /*     if (!authenticate.isAuthenticated()) {
            return new ApiResponse<>(false, "User not found");

        }*/
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new ApiResponse<>(true,
                AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build()
        );
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token
                .builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
