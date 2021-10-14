package com.algaworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.service.CadastroClienteService;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	CadastroClienteService service;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienterepository.findAll();
		
	}
	
	@GetMapping("/{clienteId}")
	public Cliente buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienterepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienterepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		
		if (clienterepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		clienterepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	public ResponseEntity<Void> remover (Long clientId) {
		if (clienterepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.excluir(clientId);
		
		return ResponseEntity.noContent().build();
	}
	
}
