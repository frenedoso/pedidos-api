package com.rest.pedidosapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rest.pedidosapi.model.ItemPedido;
import com.rest.pedidosapi.model.Pedido;
import com.rest.pedidosapi.service.PedidoService;

@RestController
@RequestMapping(value="/api")
public class PedidoResource {
	
	@Autowired
	PedidoService pedidoService;
		
	
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos() {
		return pedidoService.getPedidos();
	}
	
	@PostMapping("/pedido")
	public Pedido salvaPedido(@RequestBody @Valid Pedido pedido) {				
		try {
			return pedidoService.salvarPedido(pedido);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}		
	}
	
	@GetMapping("/pedido/{pedidoId}")
	public Pedido getPedidoById(@PathVariable long pedidoId) {
		try {
			return pedidoService.getPedido(pedidoId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@GetMapping("/pedido/{pedidoId}/itens") 
	public List<ItemPedido> getPedidoItens(@PathVariable long pedidoId) {
		return pedidoService.getPedidoItens(pedidoId);
	}
	
	@PostMapping("/pedido/{pedidoId}/itens")
	public ItemPedido salveItemPedido(@RequestBody @Valid ItemPedido itemPedido, @PathVariable long pedidoId) {
		try {
			pedidoService.salvarItemPedido(itemPedido, pedidoId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		return itemPedido;
	}
	
}
	
	

