package br.com.rodolfo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rodolfo.model.Contato;
import br.com.rodolfo.model.ContatoAlfabeto;
import br.com.rodolfo.repository.filter.ContatoFiltro;
import br.com.rodolfo.serviceLocator.ContatoServiceLocator;
import br.com.rodolfo.validation.ContatoBeanValidation;

@Controller
@RequestMapping("/agenda")
public class ContatoController {

	@Autowired
	ContatoServiceLocator contatoServiceLocator;
	
	private static final String CADASTRO_VIEW = "CadastrosAgenda";
	
	
	@RequestMapping("/novo")
	public ModelAndView novo () {
		
		ModelAndView modelAndView = new ModelAndView("CadastrosAgenda");
		
		Contato contato = new Contato();
		modelAndView.addObject(contato);
		
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar (Contato contato, Errors errors, RedirectAttributes attributes) {
		
		ContatoBeanValidation.getErrors(contato, errors);
		
		if (errors.hasErrors()) {
			
			return CADASTRO_VIEW;
			
		}
		
		contatoServiceLocator.salvar(contato);
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso !");
		
		return "redirect:/agenda/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro") ContatoFiltro filtro) {
		
		ModelAndView modelAndView = new ModelAndView ("PesquisaAgenda");
		modelAndView.addObject("listContatos", contatoServiceLocator.pesquisar(filtro.getNome()));
		
		return modelAndView;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView editar (@PathVariable("codigo") Contato contato) {
		
		ModelAndView modelAndView = new ModelAndView (CADASTRO_VIEW);
		modelAndView.addObject(contato);
		
		return modelAndView;
	}
	
	@RequestMapping (value="{codigo}", method=RequestMethod.DELETE)
	public String excluir (@PathVariable Long codigo, RedirectAttributes attributes) {

		contatoServiceLocator.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		
		return "redirect:/agenda";
	}
	
	@ModelAttribute("contatoAlfabeto")
	public List<ContatoAlfabeto> contatoAlfabeto () {
		return Arrays.asList(ContatoAlfabeto.values());
	}
}
