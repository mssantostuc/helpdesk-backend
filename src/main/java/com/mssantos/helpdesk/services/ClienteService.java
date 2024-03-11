package com.mssantos.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mssantos.helpdesk.domain.Cliente;
import com.mssantos.helpdesk.domain.Pessoa;
import com.mssantos.helpdesk.domain.dtos.ClienteDTO;
import com.mssantos.helpdesk.repositories.ClienteRepository;
import com.mssantos.helpdesk.repositories.PessoaRepository;
import com.mssantos.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.mssantos.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
		
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Cliente possui ordens de serviço e não pode ser deletado!");
		}else {
			repository.deleteById(id);
		}
		
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		 Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		 if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			 throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		 }
		 
		 obj = pessoaRepository.findByEmail(objDTO.getEmail());
		 if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			 throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		 }
	}


	
}
