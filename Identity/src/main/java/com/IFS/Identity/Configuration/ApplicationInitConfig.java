package com.IFS.Identity.Configuration;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Enums.Roles;
import com.IFS.Identity.Repositoty.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty())
            {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                var role = new HashSet<String>();
                role.add(Roles.ADMIN.name());
                User user = User.builder()
                        .userName("admin")
                        .passWord(passwordEncoder.encode("admin"))
                        .role(role)
                        .build();
                userRepository.save(user);
                log.warn("admin was create with default password (admin), please change it soon.");
            }
        };
    }
}
