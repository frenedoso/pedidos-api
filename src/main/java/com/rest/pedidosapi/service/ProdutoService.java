package com.rest.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pedidosapi.model.Produto;
import com.rest.pedidosapi.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

	public Produto getProdutoById(long id) throws Exception {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (!produto.isPresent()) {
			throw new Exception("Produto não encontrado");
		}
		return produto.get();
	}
}
