package com.api.remedios.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.api.remedios.enums.Laboratorio;
import com.api.remedios.enums.Via;
import com.api.remedios.module.RemedioEntity;

public record DadosDetalhamentoRemedioDTO(UUID id, String nome, Via via, String lote, int quantidade,
    LocalDate dataValidade, Laboratorio laboratorio, Boolean ativo) {

  public DadosDetalhamentoRemedioDTO(RemedioEntity remedio) {

    this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getQuantidade(),
        remedio.getDataValidade(), remedio.getLaboratorio(), remedio.getAtivo());
  }
}
