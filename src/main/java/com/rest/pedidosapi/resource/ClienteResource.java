package com.rest.pedidosapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pedidosapi.model.Cliente;
import com.rest.pedidosapi.service.ClienteService;

@RestController
@RequestMapping(value="/api")
public class ClienteResource {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> getProdutos() {
		return clienteService.getClientes();
	}

}
	
	

