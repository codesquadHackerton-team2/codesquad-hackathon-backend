package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.configuration.security.LoggedOnToken;
import com.codesquad.codereviewers.domain.RegisteredUser;
import com.codesquad.codereviewers.domain.service.RegisteredUserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.signingkey}")
    private String signingKey;

    @Autowired
    private RegisteredUserService userService;

    public String generateToken(LoggedOnToken token) {
        String generatedToken = null;
        try {
            generatedToken = Jwts.builder()
                    .setIssuer("codesquad")
                    .claim("USERNAME", token.getPrincipal().getNickname())
                    .claim("USER_ID", token.getPrincipal().getUserId())
                    .signWith(SignatureAlgorithm.HS256, signingKey)
                    .compact();
        }catch (Exception e) {
            log.error("error occurred while generating JWT. {}", e);
        }
        return generatedToken;
    }

    public RegisteredUser decodeToken(String token) {
        Jws<Claims> jws = null;

        try {
            jws = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            log.error("error occurred while decoding JWT: {}", e);
        }

        Long userId = jws.getBody().get("USER_ID", Long.class);
        return userService.getUserById(userId);
    }
}
