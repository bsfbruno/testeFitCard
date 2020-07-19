package br.com.bruno.estabelecimentos.service;

import java.util.Optional;

import br.com.bruno.estabelecimentos.domain.Endereco;

public interface EnderecoService {

	public void save(Endereco end);
	
	public Optional<Endereco> findById(Integer id);
	
	public void deleteById(Integer id);
}
