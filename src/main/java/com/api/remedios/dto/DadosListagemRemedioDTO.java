package com.api.remedios.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.api.remedios.enums.Laboratorio;
import com.api.remedios.module.RemedioEntity;

public record DadosListagemRemedioDTO(UUID id,String nome,int quantidade,LocalDate dataValidade,Laboratorio laboratorio ) {
  
  public DadosListagemRemedioDTO(RemedioEntity remedio) {
    this(remedio.getId(), remedio.getNome(),remedio.getQuantidade(),remedio.getDataValidade(),remedio.getLaboratorio());
  }
}
