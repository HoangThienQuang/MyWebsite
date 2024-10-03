package com.IFS.Identity.Controller;

import com.IFS.Identity.Service.AuthenticationService;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.AuthenticationRequest;
import com.IFS.Identity.dto.request.IntrospectRequest;
import com.IFS.Identity.dto.response.ApiResponseSuccess;
import com.IFS.Identity.dto.response.AuthenticationResponse;
import com.IFS.Identity.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponseSuccess<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        AuthenticationResponse authenticationResponse = authenticationService.CheckAuthenticate(request);
        return ApiResponseSuccess.<AuthenticationResponse>builder()
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .data(authenticationResponse)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponseSuccess<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);

        return ApiResponseSuccess.<IntrospectResponse>builder()
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .data(result)
                .build();
    }

}
