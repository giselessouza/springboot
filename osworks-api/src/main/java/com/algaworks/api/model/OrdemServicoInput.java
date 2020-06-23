package com.algaworks.api.model;

import java.math.BigDecimal;

public class OrdemServicoInput {

	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@Valid
	@NotNull
	private ClienteIdInput client;
	
	
	
	public ClienteIdInput getClient() {
		return client;
	}
	public void setClient(ClienteIdInput client) {
		this.client = client;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	
	
	
}
