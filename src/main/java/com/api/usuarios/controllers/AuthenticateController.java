package com.api.usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.usuarios.dto.AutenticateDataDTO;
import com.api.usuarios.dto.JWTTokenDataDTO;
import com.api.usuarios.module.UsuarioEntity;
import com.api.usuarios.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticateController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;
  
  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid AutenticateDataDTO data) {
    var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var authenticate = manager.authenticate(token);
    var tokenJWT = tokenService.generateToken((UsuarioEntity) authenticate.getPrincipal());

    return ResponseEntity.ok(new JWTTokenDataDTO(tokenJWT));
  }
}
