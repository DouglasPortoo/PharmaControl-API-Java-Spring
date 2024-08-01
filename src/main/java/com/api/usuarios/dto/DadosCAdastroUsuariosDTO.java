package com.api.usuarios.dto;

import jakarta.validation.constraints.NotBlank;


public class DadosCAdastroUsuariosDTO {

    @NotBlank(message = "Nome do usuário é obrigatório")
    private String login;

    @NotBlank(message = "Senha do usuário é obrigatória")
    private String password;
 // Construtor
 public DadosCAdastroUsuariosDTO(String login, String password) {
    this.login = login;
    this.password = password;
}

// Getters
public String getLogin() {
    return login;
}

public String getPassword() {
    return password;
}

// Setters
public void setLogin(String login) {
    this.login = login;
}

public void setPassword(String password) {
    this.password = password;
}

}
