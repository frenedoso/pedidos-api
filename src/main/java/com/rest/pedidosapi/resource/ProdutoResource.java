package com.rest.pedidosapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pedidosapi.model.Produto;
import com.rest.pedidosapi.service.ProdutoService;

@RestController
@RequestMapping(value="/api")
public class ProdutoResource {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/produtos")
	public List<Produto> getProdutos() {
		return produtoService.getProdutos();
	}
}
	
	

