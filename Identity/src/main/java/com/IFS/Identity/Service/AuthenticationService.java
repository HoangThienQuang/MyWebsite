package com.IFS.Identity.Service;

import com.IFS.Identity.Exception.AppException;
import com.IFS.Identity.Repositoty.UserRepository;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.AuthenticationRequest;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public boolean CheckAuthenticate(AuthenticationRequest request)
    {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ResponseCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassWord(), user.getPassWord());
    }
}
