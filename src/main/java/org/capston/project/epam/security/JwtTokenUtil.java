package org.capston.project.epam.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.capston.project.epam.config.SecurityProperties;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  public static final String USER = "user";
  private final SecurityProperties securityProperties;

  public JwtToken generateToken(String subject, AuthUser authUser) {
    Map<String, Object> claim = new HashMap<>();
    claim.put(USER, authUser);

    return JwtToken.builder()
        .accessToken(generateToken(subject, claim, securityProperties.getAccessTokenValiditySeconds()))
        .refreshToken(generateToken(subject, claim, securityProperties.getRefreshTokenValiditySeconds()))
        .expiresIn(securityProperties.getAccessTokenValiditySeconds())
        .build();
  }

  //todo must changed sign with Key, same method which consumes Key key
  public String generateToken(String subject, Map<String, Object> claims, long validitySeconds) {
    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + validitySeconds * 1000))
        .signWith(SignatureAlgorithm.HS512, securityProperties.getSecretKey()).compact();
  }
}