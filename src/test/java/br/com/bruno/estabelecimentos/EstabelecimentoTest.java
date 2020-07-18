package br.com.bruno.estabelecimentos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EstabelecimentoTest {
	
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

	@Test
	public void inserirEstado() {
		Estado estado1 = new Estado(null, "SP");
		Estado estado2 = new Estado(null, "MG");
		Estado estado3 = new Estado(null, "PR");
		Estado estado4 = new Estado(null, "SC");
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3, estado4));
	}
	
	@Test
	public void inserirCidade() {
		Cidade cidade1 = new Cidade(null, "São Paulo", estadoRepository.findById(1).get());
		Cidade cidade2 = new Cidade(null, "Belo Horizonte", estadoRepository.findById(2).get());
		Cidade cidade3 = new Cidade(null, "Curitiba", estadoRepository.findById(3).get());
		Cidade cidade4 = new Cidade(null, "Florianópolis", estadoRepository.findById(4).get());
		
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
	}
	
	@Test
	public void inserirEndereco() {
		Endereco end1 = new Endereco(null, "logradouro teste 1", "111", "bairro 1", cidadeRepository.findById(1).get());
		Endereco end2 = new Endereco(null, "logradouro teste 2", "222", "bairro 2", cidadeRepository.findById(2).get());
		Endereco end3 = new Endereco(null, "logradouro teste 3", "333", "bairro 3", cidadeRepository.findById(3).get());
		Endereco end4 = new Endereco(null, "logradouro teste 4", "444", "bairro 4", cidadeRepository.findById(4).get());
		Endereco end5 = new Endereco(null, "logradouro teste 5", "555", "bairro 5", cidadeRepository.findById(1).get());		
	
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5));
	}
	
	@Test
	public void inserirCategorias() {
		Categoria cat1 = new Categoria(null, "Supermercado");
		Categoria cat2 = new Categoria(null, "Restaurante");
		Categoria cat3 = new Categoria(null, "Borracharia");
		Categoria cat4 = new Categoria(null, "Posto");
		Categoria cat5 = new Categoria(null, "Oficina");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
	}
	
	@Test
	public void inserirEstabelecimento() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Estabelecimento est1 = new Estabelecimento(null, "Supermercado Teste 1", "Supermercado Fantasia 1", "42.813.947/0001-61", "teste1@email.com", "(11)1111-1111", sdf.parse("11/07/2020"), true, "111-1", "11.111-1", enderecoRepository.findById(1).get(), categoriaRepository.findById(1).get());
		Estabelecimento est2 = new Estabelecimento(null, "Restaurante Teste 1", "Restaurante Fantasia 1", "44.434.320/0001-25", "teste2@email.com", "(22)2222-2222", sdf.parse("12/07/2020"), true, "222-2", "22.222-2", enderecoRepository.findById(2).get(), categoriaRepository.findById(2).get());
		Estabelecimento est3 = new Estabelecimento(null, "Borracharia Teste 1", "Borracharia Fantasia 1", "93.605.005/0001-29", "teste3@email.com", "(33)3333-3333", sdf.parse("13/07/2020"), true, "333-3", "33.333-3", enderecoRepository.findById(3).get(), categoriaRepository.findById(3).get());
		Estabelecimento est4 = new Estabelecimento(null, "Posto Teste 1", "Posto Fantasia 1", "59.280.042/0001-76", "teste4@email.com", "(44)4444-4444", sdf.parse("14/07/2020"), true, "444-4", "44.444-4", enderecoRepository.findById(4).get(), categoriaRepository.findById(4).get());
		Estabelecimento est5 = new Estabelecimento(null, "Oficina Teste 1", "Oficina Fantasia 1", "24.000.565/0001-37", "teste5@email.com", "(55)5555-5555", sdf.parse("14/07/2020"), true, "555-5", "55.555-5", enderecoRepository.findById(5).get(), categoriaRepository.findById(5).get());
		
		estabelecimentoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5));
	}
	
	@Test
	public void deletarEstabelecimento() {
		estabelecimentoRepository.deleteAll();
	}
	
	@Test
	public void deletarEndereco() {
		enderecoRepository.deleteAll();
	}
	
	@Test
	public void deletarCategoria() {
		categoriaRepository.deleteAll();
	}
}
