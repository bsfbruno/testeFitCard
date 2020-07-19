package br.com.bruno.estabelecimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.bruno.estabelecimentos.domain.Cidade;
import br.com.bruno.estabelecimentos.repository.CidadeRepository;

@Service
public class CidadeServiceImpl implements CidadeService{

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public void save(Cidade cid) {
		cidadeRepository.save(cid);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir a cidade!");
		}		
	}

}
