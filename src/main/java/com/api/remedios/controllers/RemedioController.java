package com.api.remedios.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.remedios.dto.DadosCadastroRemedioDTO;
import com.api.remedios.module.RemedioEntity;
import com.api.remedios.repository.RemediosRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

  @Autowired
  private RemediosRepositories remediosRepositories;

  @PostMapping
  public void postMethodName(@RequestBody DadosCadastroRemedioDTO json) {
    System.out.println(json);
    RemedioEntity remedio = new RemedioEntity(json);
    System.out.println(remedio);
    remediosRepositories.save(remedio);
  }


}
