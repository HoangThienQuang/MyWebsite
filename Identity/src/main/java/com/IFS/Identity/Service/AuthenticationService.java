package com.IFS.Identity.Service;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Exception.AppException;
import com.IFS.Identity.Repositoty.UserRepository;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.AuthenticationRequest;
import com.IFS.Identity.dto.request.IntrospectRequest;
import com.IFS.Identity.dto.response.AuthenticationResponse;
import com.IFS.Identity.dto.response.IntrospectResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.*;
import lombok.experimental.NonFinal;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;

    public AuthenticationResponse CheckAuthenticate(AuthenticationRequest request)
    {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ResponseCode.USER_NOT_EXIST));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean result = passwordEncoder.matches(request.getPassWord(), user.getPassWord());
        if (!result)
            throw new AppException(ResponseCode.NOT_AUTHENTICATED);
        var token = createToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private String createToken(User user)
    {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("HTQ_author")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token");
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspectResponse(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNED_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        var verify = signedJWT.verify(jwsVerifier);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(verify && expiryTime.after(new Date()))
                .build();
    }

    private String buildScope (User user)
    {
        //create a return list string : [one two three for]
        StringJoiner stringJoiner = new StringJoiner("");
        if(!CollectionUtils.isEmpty(user.getRole()))
        {
            user.getRole().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
