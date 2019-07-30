package com.rest.pedidosapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rest.pedidosapi.model.Produto;
import com.rest.pedidosapi.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value = "API de Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna uma lista de Produtos")
	public List<Produto> getProdutos() {
		return produtoService.getProdutos();
	}
	
	@GetMapping("/produtos/{id}")
	@ApiOperation(value = "Retorna um produto pelo id")
	public Produto getProduto(@PathVariable long id) {
		try {
			return produtoService.getProdutoById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
}
	
	

