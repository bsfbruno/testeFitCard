package br.com.bruno.estabelecimentos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bruno.estabelecimentos.domain.Endereco;
import br.com.bruno.estabelecimentos.repository.EnderecoRepository;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void save(Endereco end) {
		enderecoRepository.save(end);		
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Endereco> findById(Integer id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		return endereco;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			enderecoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir o endereço!");
		}		
	}

}
