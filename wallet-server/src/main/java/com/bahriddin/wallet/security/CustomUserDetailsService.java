//package com.bahriddin.wallet.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import saiga.model.User;
//import saiga.repository.UserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByPhoneNumber(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User with phone number " + username + " not found"));
//    }
//}
