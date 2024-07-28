package com.api.remedios.dto;

import com.api.remedios.enums.Laboratorio;
import com.api.remedios.enums.Via;

public record DadosCadastroRemedioDTO(
    String nome,
    Via via,
    String lote,
    String quantidade,
    String dataValidade,
    Laboratorio laboratorio
    ){}
