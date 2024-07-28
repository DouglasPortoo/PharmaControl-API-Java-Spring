package com.api.remedios.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.remedios.module.RemedioEntity;

public interface RemediosRepositories extends JpaRepository<RemedioEntity, UUID> {
  
}
