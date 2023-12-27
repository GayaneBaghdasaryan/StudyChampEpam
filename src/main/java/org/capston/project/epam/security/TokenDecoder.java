package org.capston.project.epam.security;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.DecodingException;
import org.springframework.security.authentication.BadCredentialsException;

import java.security.PublicKey;

public final class TokenDecoder {
  private static final ObjectMapper mapper = new ObjectMapper();
  private final Jws<Claims> claims;

  private TokenDecoder(Jws<Claims> claims) {
    this.claims = claims;
  }

  public static TokenDecoder of(PublicKey publicKey, String token) {
    try {
      Jws<Claims> claims = parseClaims(token, publicKey);
      return new TokenDecoder(claims);
    } catch (ExpiredJwtException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException |
             DecodingException var3) {
      throw new BadCredentialsException("TOKEN_INVALID");
    }
  }

  public static TokenDecoder of(String publicKey, String token) {
    try {
      Jws<Claims> claims = parseClaims(token, publicKey);
      return new TokenDecoder(claims);
    } catch (ExpiredJwtException e) {
      throw new BadCredentialsException("TOKEN_INVALID");
    } catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException | DecodingException var3) {
      throw new BadCredentialsException("TOKEN_INVALID");
    }
  }

  private static Jws<Claims> parseClaims(String token, PublicKey key)
      throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }

  private static Jws<Claims> parseClaims(String token, String key)
      throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }

  public String username() {
    return this.claims.getBody().getSubject();
  }

  public AuthUser user() {
    try {
      return mapper.convertValue(this.claims.getBody().get("user"), AuthUser.class);
    } catch (IllegalArgumentException var2) {
      throw new BadCredentialsException("TOKEN_INVALID");
    }
  }
}
