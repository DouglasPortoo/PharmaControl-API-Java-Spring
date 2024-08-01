package com.api.usuarios.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.usuarios.dto.DadosCAdastroUsuariosDTO;
import com.api.usuarios.module.UsuarioEntity;
import com.api.usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UserController {
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping
  @Transactional
  public ResponseEntity<DadosCAdastroUsuariosDTO> cadastrar(@RequestBody @Valid DadosCAdastroUsuariosDTO dados) {

    var encodedPassword = passwordEncoder.encode(dados.getPassword());

    dados.setPassword(encodedPassword);
      
    var usuario = new UsuarioEntity(dados);

    usuarioRepository.save(usuario);

    return ResponseEntity.ok().build();
  }
  
}
