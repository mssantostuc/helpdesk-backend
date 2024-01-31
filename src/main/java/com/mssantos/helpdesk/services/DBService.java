package com.mssantos.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssantos.helpdesk.domain.Chamado;
import com.mssantos.helpdesk.domain.Cliente;
import com.mssantos.helpdesk.domain.Tecnico;
import com.mssantos.helpdesk.domain.enums.Perfil;
import com.mssantos.helpdesk.domain.enums.Prioridade;
import com.mssantos.helpdesk.domain.enums.Status;
import com.mssantos.helpdesk.repositories.ChamadoRepository;
import com.mssantos.helpdesk.repositories.ClienteRepository;
import com.mssantos.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
		
	public void instaciaDB() {
		Tecnico tec1 = new Tecnico(null,"Marcio Santos","76155390215","mssantostuc@gmail.com","123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null,"Linus Torvalds","80527954780","torvalds@mail.com","123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
