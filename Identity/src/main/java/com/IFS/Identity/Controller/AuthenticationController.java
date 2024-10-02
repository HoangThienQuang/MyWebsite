package com.IFS.Identity.Controller;

import com.IFS.Identity.Service.AuthenticationService;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.AuthenticationRequest;
import com.IFS.Identity.dto.response.ApiResponseSuccess;
import com.IFS.Identity.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("log-in")
    ApiResponseSuccess<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        boolean result = authenticationService.CheckAuthenticate(request);
        return ApiResponseSuccess.<AuthenticationResponse>builder()
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .data(AuthenticationResponse.builder()
                        .isAuthenticated(result)
                        .build())
                .build();
    }

}
