package br.com.casadocodigo2.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo2.loja.daos.ProdutoDAO;
import br.com.casadocodigo2.loja.models.Produto;

@Controller
public class HomeController {

	
	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("/")
	@Cacheable(value="produtoHome")
	public ModelAndView index() {
		System.out.println("Entrando na home da casa do codigo");
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos",produtos);
		return modelAndView; 
	}
}
