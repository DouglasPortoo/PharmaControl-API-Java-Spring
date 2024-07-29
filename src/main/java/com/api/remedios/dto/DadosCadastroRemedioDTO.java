package com.api.remedios.dto;

import java.time.LocalDate;

import com.api.remedios.enums.Laboratorio;
import com.api.remedios.enums.Via;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroRemedioDTO(

    @NotBlank(message = "Nome do remédio é obrigatório")
    String nome,
    @Enumerated
    Via via,
    @NotBlank(message = "Lote do remédio é obrigatório")
    String lote,
    @NotNull(message = "Quantidade do remédio é obrigatória")
    Integer quantidade,
    @Future(message = "Data de validade deve ser futura")
    LocalDate dataValidade,
    @Enumerated 
    Laboratorio laboratorio
    ){}
