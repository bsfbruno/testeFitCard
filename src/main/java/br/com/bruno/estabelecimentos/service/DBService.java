package br.com.bruno.estabelecimentos.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruno.estabelecimentos.domain.Categoria;
import br.com.bruno.estabelecimentos.domain.Estado;
import br.com.bruno.estabelecimentos.repository.CategoriaRepository;
import br.com.bruno.estabelecimentos.repository.EstadoRepository;

@Service
public class DBService {
	
	@Autowired
	private EstadoRepository estadoRepository;
		
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	public void instantiateDataBase() throws ParseException {
		Estado estado1 = new Estado(null, "SP");
		Estado estado2 = new Estado(null, "MG");
		Estado estado3 = new Estado(null, "PR");
		Estado estado4 = new Estado(null, "SC");
		
		Categoria cat1 = new Categoria(null, "Borracharia");
		Categoria cat2 = new Categoria(null, "Oficina");
		Categoria cat3 = new Categoria(null, "Posto");
		Categoria cat4 = new Categoria(null, "Restaurante");
		Categoria cat5 = new Categoria(null, "Supermercado");
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3, estado4));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));		
	}

}
