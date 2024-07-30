package com.api.remedios.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.remedios.dto.DadosAtualizarRemedioDTO;
import com.api.remedios.dto.DadosCadastroRemedioDTO;
import com.api.remedios.dto.DadosDetalhamentoRemedioDTO;
import com.api.remedios.dto.DadosListagemRemedioDTO;
import com.api.remedios.module.RemedioEntity;
import com.api.remedios.repository.RemediosRepositories;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

  @Autowired
  private RemediosRepositories remediosRepositories;

  @PostMapping
  @Transactional
  public ResponseEntity<DadosDetalhamentoRemedioDTO> cadastrar(@RequestBody @Valid DadosCadastroRemedioDTO dados,
      UriComponentsBuilder uriBuilder) {

    var remedio = new RemedioEntity(dados);

    remediosRepositories.save(remedio);

    var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

    return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedioDTO(remedio));
  }

  @GetMapping
  public ResponseEntity<List<DadosListagemRemedioDTO>> listar() {
    var remedio = remediosRepositories.findAllByAtivoTrue().stream().map(DadosListagemRemedioDTO::new).toList();
    return ResponseEntity.ok(remedio);
  }

  @GetMapping ("/{id}")
  public ResponseEntity<DadosListagemRemedioDTO> listarPorId(@PathVariable UUID id) {
    var remedio = remediosRepositories.getReferenceById(id);
    return ResponseEntity.ok(new DadosListagemRemedioDTO(remedio));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DadosDetalhamentoRemedioDTO> atualizar(@RequestBody @Valid DadosAtualizarRemedioDTO dados) {
    var remedio = remediosRepositories.getReferenceById(dados.id());
    remedio.atualizarInformacoes(dados);
    return ResponseEntity.ok(new DadosDetalhamentoRemedioDTO(remedio));
  }

  @PutMapping("ativar/{id}")
  @Transactional
  public ResponseEntity<Void> ativar(@PathVariable UUID id) {
    var remedio = remediosRepositories.getReferenceById(id);
    remedio.ativar();

    return ResponseEntity.noContent().build();
  }

  @PutMapping("inativar/{id}")
  @Transactional
  public ResponseEntity<Void> inativar(@PathVariable UUID id) {
    var remedio = remediosRepositories.getReferenceById(id);
    remedio.inativar();
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> deletar(@PathVariable UUID id) {
    remediosRepositories.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
