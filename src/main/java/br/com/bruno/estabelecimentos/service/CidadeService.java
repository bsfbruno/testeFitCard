package br.com.bruno.estabelecimentos.service;

import br.com.bruno.estabelecimentos.domain.Cidade;

public interface CidadeService {

	public void save(Cidade cid);

	public void deleteById(Integer id);
}
