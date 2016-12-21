package br.com.rodolfo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rodolfo.model.StatusTitulo;
import br.com.rodolfo.model.Titulo;
import br.com.rodolfo.repository.filter.TituloFiltro;
import br.com.rodolfo.repository.filter.ValoresPagosFiltro;
import br.com.rodolfo.serviceLocator.CadastroTituloService;
import br.com.rodolfo.util.Processamento;

@Controller
@RequestMapping ("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastrosTitulos";
	
	@Autowired
	private CadastroTituloService cadastroTituloService;

	
	@RequestMapping("/novo")
	public ModelAndView novo () {
		ModelAndView modelAndView = new ModelAndView (CADASTRO_VIEW);
		
		Titulo titulo = new Titulo();
		
		modelAndView.addObject(titulo);
		return modelAndView;
	}
	
	@RequestMapping (method = RequestMethod.POST)
	public String salvar (@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		
		if (titulo.isRecebido()) {
			
			if (titulo.isValorPagamentoValido()) {
				
				errors.rejectValue("valorPago", null, "Valor de pagamento está menor que o valor real");
				
			}
			
			if (titulo.getDataPagamento() == null) {
				
				errors.rejectValue("dataPagamento", null, "A data de pagamento é obrigatória");
								
			}
			
			if(titulo.getValorPago() == null) {
				
				errors.rejectValue("valorPago", null, "O valor de pagamento é obrigatório");
				
			}
						
			
		}
		
		if (errors.hasErrors() ) {
			
			return CADASTRO_VIEW;
			
		}
		
		try {
			
			cadastroTituloService.salvar(titulo);
		
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso !");
		
			return "redirect:/titulos/novo";
		
		} catch(IllegalArgumentException e) {
			
			errors.rejectValue("dataVencimento", null, e.getMessage());
			
			return CADASTRO_VIEW;
		} 
	}
	
	@RequestMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro") TituloFiltro filtro) {
		
		List<Titulo> consulta = cadastroTituloService.pesquisarPelaData(filtro);
		
		ValoresPagosFiltro valores = Processamento.CalcularValoresPagosRealJuros(consulta);
		
		ModelAndView modelAndView = new ModelAndView ("PesquisaTitulos");
		modelAndView.addObject("listTitulos", consulta);
		modelAndView.addObject("listValores", valores);
		
		return modelAndView;
	}
	
	@RequestMapping ("{codigo}")
	public ModelAndView editar (@PathVariable("codigo") Titulo titulo) {
		
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(titulo);
		
		return modelAndView;
	}
	
	@RequestMapping (value="{codigo}", method=RequestMethod.DELETE)
	public String excluir (@PathVariable Long codigo, RedirectAttributes attributes) {

		cadastroTituloService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		
		return "redirect:/titulos";
	}
	
	@RequestMapping (value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {	
		return cadastroTituloService.receber(codigo);
	}
	
	@ModelAttribute ("statusTitulos")
	public List<StatusTitulo> statusTitulos () {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
