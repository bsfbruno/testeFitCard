package br.com.bruno.estabelecimentos.service;

import java.util.List;
import java.util.Optional;

import br.com.bruno.estabelecimentos.domain.Estabelecimento;

public interface EstabelecimentoService {

	public List<Estabelecimento> findAll();

	public void save(Estabelecimento estabelecimento);
	
	public Optional<Estabelecimento> findById(Integer id);

	public void deleteById(Integer id);
}
