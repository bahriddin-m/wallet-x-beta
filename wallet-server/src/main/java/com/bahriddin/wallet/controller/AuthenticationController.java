package com.bahriddin.wallet.controller;

import com.bahriddin.wallet.auth.AuthenticationRequest;
import com.bahriddin.wallet.auth.AuthenticationService;
import com.bahriddin.wallet.auth.LogoutService;
import com.bahriddin.wallet.auth.RegisterRequest;
import com.bahriddin.wallet.payload.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final LogoutService logoutService;

    @PostMapping("signup")
    public ResponseEntity<?> signup(
            @RequestBody RegisterRequest request
    ) {
        ApiResponse<?> signup = authService.signup(request);
        return ResponseEntity.status(signup.isSuccess() ? HttpStatus.CREATED : HttpStatus.FORBIDDEN).body(signup);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request
    ) {
        ApiResponse<?> login = authService.login(request);
        return ResponseEntity.status(login.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.FORBIDDEN).body(login);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutService.logout(request, response, authentication);
        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully logged out"));
    }


}
