package com.api.usuarios.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.usuarios.module.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID>{

  UserDetails findByLogin(String login);
  
}
