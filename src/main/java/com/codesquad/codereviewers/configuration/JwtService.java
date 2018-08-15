package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.configuration.security.LoggedOnToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.signingkey}")
    private String signingKey;

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

}
