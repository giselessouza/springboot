package com.algaworks.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.domain.exception.NegocioException;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = repository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("ja existe um cliente cadastrado com esse email");
		}
		
		return repository.save(cliente);
	}
	
	public void excluir(Long clientId) {
		repository.deleteById(clientId);
	}
	
}
