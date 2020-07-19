package br.com.bruno.estabelecimentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bruno.estabelecimentos.domain.Estabelecimento;
import br.com.bruno.estabelecimentos.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService{

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Estabelecimento> findAll() {
		List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
		return estabelecimentos;
	}

	@Override
	public void save(Estabelecimento estabelecimento) {
		estabelecimentoRepository.save(estabelecimento);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Estabelecimento> findById(Integer id) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		return estabelecimento;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			estabelecimentoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir o estabelecimento!");
		}		
	}

}
