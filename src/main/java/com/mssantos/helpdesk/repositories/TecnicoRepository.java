package com.mssantos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mssantos.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
