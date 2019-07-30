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

import com.rest.pedidosapi.model.Cliente;
import com.rest.pedidosapi.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value = "API de Clientes")
@CrossOrigin(origins = "*")
public class ClienteResource {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/clientes")
	@ApiOperation(value = "Retorna uma lista de clientes")
	public List<Cliente> getClientes() {
		return clienteService.getClientes();
	}
	
	@GetMapping("/clientes/{id}")
	@ApiOperation(value = "Retorna um cliente pelo id")
	public Cliente getCliente(@PathVariable long id) {
		try {
			return clienteService.getClienteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
	
	

