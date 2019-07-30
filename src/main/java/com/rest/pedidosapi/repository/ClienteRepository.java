package com.rest.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pedidosapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
