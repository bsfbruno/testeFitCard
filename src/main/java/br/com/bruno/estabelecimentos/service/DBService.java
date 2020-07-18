package br.com.bruno.estabelecimentos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruno.estabelecimentos.domain.Categoria;
import br.com.bruno.estabelecimentos.domain.Cidade;
import br.com.bruno.estabelecimentos.domain.Endereco;
import br.com.bruno.estabelecimentos.domain.Estabelecimento;
import br.com.bruno.estabelecimentos.domain.Estado;
import br.com.bruno.estabelecimentos.repository.CategoriaRepository;
import br.com.bruno.estabelecimentos.repository.CidadeRepository;
import br.com.bruno.estabelecimentos.repository.EnderecoRepository;
import br.com.bruno.estabelecimentos.repository.EstabelecimentoRepository;
import br.com.bruno.estabelecimentos.repository.EstadoRepository;

@Service
public class DBService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	public void instantiateDataBase() throws ParseException {
		Estado estado1 = new Estado(null, "SP");
		Estado estado2 = new Estado(null, "MG");
		Estado estado3 = new Estado(null, "PR");
		Estado estado4 = new Estado(null, "SC");
		
		/*Cidade cidade1 = new Cidade(null, "São Paulo", estado1);
		Cidade cidade2 = new Cidade(null, "Belo Horizonte", estado2);
		Cidade cidade3 = new Cidade(null, "Curitiba", estado3);
		Cidade cidade4 = new Cidade(null, "Florianópolis", estado4);
		
		Endereco end1 = new Endereco(null, "logradouro teste 1", "111", "bairro 1", cidade1);
		Endereco end2 = new Endereco(null, "logradouro teste 2", "222", "bairro 2", cidade2);
		Endereco end3 = new Endereco(null, "logradouro teste 3", "333", "bairro 3", cidade3);
		Endereco end4 = new Endereco(null, "logradouro teste 4", "444", "bairro 4", cidade4);
		Endereco end5 = new Endereco(null, "logradouro teste 5", "555", "bairro 5", cidade1);*/
		
		Categoria cat1 = new Categoria(null, "Supermercado");
		Categoria cat2 = new Categoria(null, "Restaurante");
		Categoria cat3 = new Categoria(null, "Borracharia");
		Categoria cat4 = new Categoria(null, "Posto");
		Categoria cat5 = new Categoria(null, "Oficina");
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Estabelecimento est1 = new Estabelecimento(null, "Supermercado Teste 1", "Supermercado Fantasia 1", "42.813.947/0001-61", "teste1@email.com", "(11)1111-1111", sdf.parse("11/07/2020"), true, "111-1", "11.111-1", end1, cat1);
		Estabelecimento est2 = new Estabelecimento(null, "Restaurante Teste 1", "Restaurante Fantasia 1", "44.434.320/0001-25", "teste2@email.com", "(22)2222-2222", sdf.parse("12/07/2020"), true, "222-2", "22.222-2", end2, cat2);
		Estabelecimento est3 = new Estabelecimento(null, "Borracharia Teste 1", "Borracharia Fantasia 1", "93.605.005/0001-29", "teste3@email.com", "(33)3333-3333", sdf.parse("13/07/2020"), true, "333-3", "33.333-3", end3, cat3);
		Estabelecimento est4 = new Estabelecimento(null, "Posto Teste 1", "Posto Fantasia 1", "59.280.042/0001-76", "teste4@email.com", "(44)4444-4444", sdf.parse("14/07/2020"), true, "444-4", "44.444-4", end4, cat4);
		Estabelecimento est5 = new Estabelecimento(null, "Oficina Teste 1", "Oficina Fantasia 1", "24.000.565/0001-37", "teste5@email.com", "(55)5555-5555", sdf.parse("14/07/2020"), true, "555-5", "55.555-5", end5, cat5);
		*/				
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3, estado4));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		/*cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5));		
		estabelecimentoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5));*/
	}

}
