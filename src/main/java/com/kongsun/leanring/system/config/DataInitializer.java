package com.kongsun.leanring.system.config;

import com.kongsun.leanring.system.user.Role;
import com.kongsun.leanring.system.user.User;
import com.kongsun.leanring.system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        if(userRepository.findByUsername("admin").isEmpty()){

            userRepository.save(
                    User.builder()
                    .firstname("admin")
                    .lastname("admin")
                    .username("admin")
                    .password(passwordEncoder.encode("admin12345"))
                    .role(Role.ADMIN)
                    .build());
        }
    }
}
