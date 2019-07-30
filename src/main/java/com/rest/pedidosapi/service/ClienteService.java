package com.rest.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pedidosapi.model.Cliente;
import com.rest.pedidosapi.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> getClienteById(long id) {
		return clienteRepository.findById(id);
	}
}
