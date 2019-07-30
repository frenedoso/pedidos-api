package com.rest.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pedidosapi.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
