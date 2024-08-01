package com.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.usuarios.repository.UsuarioRepository;
import com.api.usuarios.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        var tokenJWT = recoverToken(request);

        if(tokenJWT != null){
        var subject = tokenService.getSubject(tokenJWT);
        var usuario = userService.findByLogin(subject);

        var authentication = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

        filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");

    if(authorizationHeader != null ){
      return authorizationHeader.replace("Bearer ", "");
    }

    return null;
  }
  
}