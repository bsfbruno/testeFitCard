package br.com.bruno.estabelecimentos.service;

import java.util.List;
import java.util.Optional;

import br.com.bruno.estabelecimentos.domain.Estado;

public interface EstadoService {

	public List<Estado> findAll();
	
	public Optional<Estado> findById(Integer id);
}
