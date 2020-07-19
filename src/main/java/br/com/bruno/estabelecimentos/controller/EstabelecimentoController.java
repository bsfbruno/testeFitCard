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
import br.com.bruno.estabelecimentos.service.CategoriaService;
import br.com.bruno.estabelecimentos.service.CidadeService;
import br.com.bruno.estabelecimentos.service.EnderecoService;
import br.com.bruno.estabelecimentos.service.EstabelecimentoService;
import br.com.bruno.estabelecimentos.service.EstadoService;

@Controller
@RequestMapping("estabelecimentos")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/listar")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("estabelecimentos", estabelecimentoService.findAll());
		return new ModelAndView("list", model);
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView preSalvar(Estabelecimento estabelecimento) {
		ModelAndView modelAndView = new ModelAndView("add");
		List<Categoria> cat = categoriaService.findAll();
		List<Estado> est = estadoService.findAll();
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
				
		Optional<Estado> est = estadoService.findById(estabelecimento.getEndereco().getCidade().getEstado().getId());
		Estado estado = new Estado(est.get().getId(), est.get().getNome());
		Cidade cid = new Cidade(null, estabelecimento.getEndereco().getCidade().getNome(), estado);
		cidadeService.save(cid);
		Endereco end = new Endereco(null, estabelecimento.getEndereco().getLogradouro(), estabelecimento.getEndereco().getNumero(), estabelecimento.getEndereco().getBairro(), cid);
		enderecoService.save(end);
		estabelecimento.setEndereco(end);
		estabelecimentoService.save(estabelecimento);
		attr.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return new ModelAndView("redirect:/estabelecimentos/listar");
	}
	
	@GetMapping("/{id}/atualizar")
	public ModelAndView preAtualizar(@PathVariable("id") Integer id, ModelMap model) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoService.findById(id);
		Estabelecimento estab = estabelecimento.get();
		return preSalvar(estab);
	}
	
	@PutMapping("/salvar")
	public ModelAndView atualizar(@Valid @ModelAttribute("estabelecimentos") Estabelecimento estabelecimento,
			BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return preSalvar(estabelecimento);
		}
		
		Optional<Estado> est = estadoService.findById(estabelecimento.getEndereco().getCidade().getEstado().getId());
		Estado estado = new Estado(est.get().getId(), est.get().getNome());
		Cidade cid = new Cidade(null, estabelecimento.getEndereco().getCidade().getNome(), estado);
		cidadeService.save(cid);
		Endereco end = new Endereco(null, estabelecimento.getEndereco().getLogradouro(), estabelecimento.getEndereco().getNumero(), estabelecimento.getEndereco().getBairro(), cid);
		enderecoService.save(end);
		estabelecimento.setEndereco(end);
		estabelecimentoService.save(estabelecimento);
		attr.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
		return new ModelAndView("redirect:/estabelecimentos/listar");
	}
	
	@GetMapping("/{id}/detalhes")
	public ModelAndView detalhes(@PathVariable("id") Integer id, ModelMap model) {
		Estabelecimento est = estabelecimentoService.findById(id).get();
		model.addAttribute("estabelecimento", est);
		return new ModelAndView("details", model);
	}
	
	@GetMapping("/{id}/excluir")
	public ModelAndView remove(@PathVariable("id") Integer id, RedirectAttributes attr) {
		Estabelecimento est = estabelecimentoService.findById(id).get();
		Integer end = est.getEndereco().getId();
		estabelecimentoService.deleteById(id);
		attr.addFlashAttribute("id", end);
		return new ModelAndView("redirect:/estabelecimentos/excluir/endereco" );
	}
	
	@GetMapping("/excluir/endereco")
	public ModelAndView removeEndereco(@ModelAttribute("id") Integer id, RedirectAttributes attr) {
		Endereco endereco = enderecoService.findById(id).get();
		Integer cidId = endereco.getCidade().getId();
		enderecoService.deleteById(id); 
		attr.addFlashAttribute("id", cidId);
		return new ModelAndView("redirect:/estabelecimentos/excluir/cidade");
	}
	
	@GetMapping("/excluir/cidade")
	public ModelAndView removeCidade(@ModelAttribute("id") Integer id, RedirectAttributes attr) {
		cidadeService.deleteById(id); 
		attr.addFlashAttribute("mensagem", "Estabelecimento excluído com sucesso!");
		return new ModelAndView("redirect:/estabelecimentos/listar");
	}
}
