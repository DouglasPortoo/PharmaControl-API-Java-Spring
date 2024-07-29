package com.api.remedios.dto;

import java.util.UUID;

import com.api.remedios.enums.Laboratorio;
import com.api.remedios.enums.Via;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedioDTO(@NotNull(message = "ID é obrigatório") UUID id,String nome, Via via, Laboratorio laboratorio) {
}
