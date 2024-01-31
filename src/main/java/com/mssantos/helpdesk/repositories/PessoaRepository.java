package com.mssantos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mssantos.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
