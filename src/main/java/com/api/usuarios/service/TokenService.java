package com.api.usuarios.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.api.usuarios.module.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

  public String generateToken(UsuarioEntity usuario) {
    try {
      var algorithm = Algorithm.HMAC256("123456");
      return JWT.create()
          .withIssuer("auth0")
          .withSubject(usuario.getLogin())
          .withClaim("id", usuario.getId().toString())
          .withExpiresAt(expireDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("erro ao gerar token", exception);
    }
  }

  
  public String getSubject(String tokenJWT) {
    try {
      var algorithm = Algorithm.HMAC256("123456");
      return JWT.require(algorithm)
      .withIssuer("auth0")
      .build()
      .verify(tokenJWT)
      .getSubject();

  } catch (JWTCreationException exception){
    throw new RuntimeException("token invalido ou expirado!");
  }
  }

  private Instant expireDate() {
    return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
  }
}
