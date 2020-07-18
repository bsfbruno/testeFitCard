package br.com.bruno.estabelecimentos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@Controller
@RequestMapping("estabelecimentos")
public class EstabelecimentoController {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/listar")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("estabelecimentos", estabelecimentoRepository.findAll());
		return new ModelAndView("list", model);
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView preSalvar(Estabelecimento estabelecimento) {
		ModelAndView modelAndView = new ModelAndView("add");
		List<Categoria> cat = categoriaRepository.findAll();
		List<Estado> est = estadoRepository.findAll();
		modelAndView.addObject("estabelecimentos", estabelecimento);
		modelAndView.addObject("categorias", cat);
		modelAndView.addObject("estados", est);
		return modelAndView;
	}
	
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid @ModelAttribute("estabelecimentos") Estabelecimento estabelecimento,
			BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return preSalvar(estabelecimento);
		}
				
		Optional<Estado> est = estadoRepository.findById(estabelecimento.getEndereco().getCidade().getEstado().getId());
		Estado estado = new Estado(est.get().getId(), est.get().getNome());
		Cidade cid = new Cidade(null, estabelecimento.getEndereco().getCidade().getNome(), estado);
		cidadeRepository.save(cid);
		Endereco end = new Endereco(null, estabelecimento.getEndereco().getLogradouro(), estabelecimento.getEndereco().getNumero(), estabelecimento.getEndereco().getBairro(), cid);
		enderecoRepository.save(end);
		estabelecimento.setEndereco(end);
		estabelecimentoRepository.save(estabelecimento);
		attr.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return new ModelAndView("redirect:/estabelecimentos/listar");
	}
	
	@GetMapping("/{id}/atualizar")
	public ModelAndView preAtualizar(@PathVariable("id") Integer id, ModelMap model) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		Estabelecimento estab = estabelecimento.get();
		return preSalvar(estab);
	}
	
	@PutMapping("/salvar")
	public ModelAndView atualizar(@Valid @ModelAttribute("estabelecimentos") Estabelecimento estabelecimento,
			BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return preSalvar(estabelecimento);
		}
		
		Optional<Estado> est = estadoRepository.findById(estabelecimento.getEndereco().getCidade().getEstado().getId());
		Estado estado = new Estado(est.get().getId(), est.get().getNome());
		Cidade cid = new Cidade(null, estabelecimento.getEndereco().getCidade().getNome(), estado);
		cidadeRepository.save(cid);
		Endereco end = new Endereco(null, estabelecimento.getEndereco().getLogradouro(), estabelecimento.getEndereco().getNumero(), estabelecimento.getEndereco().getBairro(), cid);
		enderecoRepository.save(end);
		estabelecimento.setEndereco(end);
		estabelecimentoRepository.save(estabelecimento);
		attr.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return new ModelAndView("redirect:/estabelecimentos/listar");
	}
	
	@GetMapping("/{id}/detalhes")
	public ModelAndView detalhes(@PathVariable("id") Integer id, ModelMap model) {
		Estabelecimento est = estabelecimentoRepository.findById(id).get();
		model.addAttribute("estabelecimento", est);
		return new ModelAndView("details", model);
	}
	
	@GetMapping("/{id}/excluir")
	public ModelAndView remove(@PathVariable("id") Integer id, RedirectAttributes attr) {
		Estabelecimento est = estabelecimentoRepository.findById(id).get();
		Integer end = est.getEndereco().getId();
		estabelecimentoRepository.deleteById(id);
		attr.addFlashAttribute("id", end);
		return new ModelAndView("redirect:/estabelecimentos/excluir/endereco" );
	}
	
	@GetMapping("/excluir/endereco")
	public ModelAndView removeEndereco(@ModelAttribute("id") Integer id, RedirectAttributes attr) {
		Endereco endereco = enderecoRepository.findById(id).get();
		Integer cidId = endereco.getCidade().getId();
		enderecoRepository.deleteById(id); 
		attr.addFlashAttribute("id", cidId);
		return new ModelAndView("redirect:/estabelecimentos/excluir/cidade");
	}
	
	@GetMapping("/excluir/cidade")
	public String removeCidade(@ModelAttribute("id") Integer id ) {
		cidadeRepository.deleteById(id); 
		return "redirect:/estabelecimentos/listar";
	}
}
