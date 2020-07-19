package br.com.bruno.estabelecimentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bruno.estabelecimentos.domain.Estado;
import br.com.bruno.estabelecimentos.repository.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService{

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Estado> findAll() {
		List<Estado> estados = estadoRepository.findAll();
		return estados;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Estado> findById(Integer id) {
		Optional<Estado> estados = estadoRepository.findById(id);
		return estados;
	}

}
