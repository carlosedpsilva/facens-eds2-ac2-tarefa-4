package br.facens.eds2.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.facens.eds2.gateway.repository.entity.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> { }