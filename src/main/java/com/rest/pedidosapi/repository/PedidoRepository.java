package com.rest.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pedidosapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
