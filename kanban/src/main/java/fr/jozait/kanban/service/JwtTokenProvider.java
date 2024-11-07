/* (C)2024 */
package fr.jozait.kanban.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${api.jwtSecret}")
  private String jwtSecret;

  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();

    return Jwts.builder().subject(username).issuedAt(currentDate).signWith(key()).compact();
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public Boolean validateToken(String token) {
    Jwts.parser().verifyWith(key()).build().parse(token);
    return true;
  }

  public String getUsername(String token) {

    return Jwts.parser()
        .verifyWith(key())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }
}
