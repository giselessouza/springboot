package com.algaworks.domain.repository;

@Repository
public interface ClienteRepository extends JPARepository<Cliente, Long>{

	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	List findByEmail(String email);
}
