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
	
	public Cliente getClienteById(long id) throws Exception {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (!cliente.isPresent()) {
			throw new Exception("Cliente não encontrado");
		}
		
		return cliente.get();
	}
}
