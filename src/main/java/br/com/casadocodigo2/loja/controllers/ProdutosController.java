package br.com.casadocodigo2.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo2.loja.daos.ProdutoDAO;
import br.com.casadocodigo2.loja.infra.FileSaver;
import br.com.casadocodigo2.loja.models.Produto;
import br.com.casadocodigo2.loja.models.TipoPreco;
import br.com.casadocodigo2.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private FileSaver fileSaver; 

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	//@RequestMapping(value="/produtos",method=RequestMethod.GET)
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

	//@RequestMapping("produtos/form")
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}

	//@RequestMapping(value="/produtos",method=RequestMethod.POST)
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="produtoHome",allEntries=true) 
	public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println("Nome orignal do arquivo"+sumario.getOriginalFilename());
		
		if (result.hasErrors()) {
			return form(produto);	
		}
		
		String path = fileSaver.write("arquivo-sumario", sumario);
		
		produto.setSumarioPath(path);
		
		produtoDAO.gravar(produto);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		//modelAndView.addObject("sucesso", "Produto Cadastrado com sucesso !!");
		redirectAttributes.addFlashAttribute("sucesso","Produtoo Cadastrado com sucesso");
		
		return modelAndView;
	}
	
	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	//jackson faz a modificacao de objeto java para json
	/*
	@RequestMapping("/{id}")
	@ResponseBody
	public Produto detalheJson(@PathVariable("id") Integer id) {
		return produtoDAO.find(id);
	}*/
}
