package com.rest.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pedidosapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
