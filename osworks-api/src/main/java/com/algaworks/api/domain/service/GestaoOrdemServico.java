package com.algaworks.api.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.api.model.Comentario;
import com.algaworks.domain.exception.NegocioException;
import com.algaworks.domain.model.Cliente;
import com.algaworks.domain.model.OrdemServico;
import com.algaworks.domain.model.StatusOrdemServico;
import com.algaworks.domain.repository.ClienteRepository;
import com.algaworks.domain.repository.ComentarioRepository;
import com.algaworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServico {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired ClienteRepository clienteRepository;
	
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("cliente nao encotrado."));
		
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return repository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		repository.save(ordemServico);
		
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = repository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("ordem de servico nao encontrada"));
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.getDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
	
}
