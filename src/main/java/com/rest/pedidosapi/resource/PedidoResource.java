package com.rest.pedidosapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
@Api(value = "API de Pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService pedidoService;
			
	@GetMapping("/pedidos")
	@ApiOperation(value="Retorna uma lista de pedidos")
	public List<Pedido> getPedidos() {
		return pedidoService.getPedidos();
	}
	
	@PostMapping("/pedidos")
	@ApiOperation(value="Salva um pedido")
	public Pedido salvaPedido(@RequestBody @Valid Pedido pedido) {				
		try {
			return pedidoService.salvarPedido(pedido);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}		
	}
	
	@GetMapping("/pedidos/{pedidoId}")
	@ApiOperation(value="Retorna um pedido pelo id")
	public Pedido getPedidoById(@PathVariable long pedidoId) {
		try {
			return pedidoService.getPedido(pedidoId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@GetMapping("/pedidos/{pedidoId}/itens")
	@ApiOperation(value="Retorna os itens de um pedido")
	public List<ItemPedido> getPedidoItens(@PathVariable long pedidoId) {
		return pedidoService.getPedidoItens(pedidoId);
	}
	
	@PostMapping("/pedidos/{pedidoId}/itens")
	@ApiOperation(value="Salva um item em um pedido")
	public ItemPedido salveItemPedido(@RequestBody @Valid ItemPedido itemPedido, @PathVariable long pedidoId) {
		try {
			pedidoService.salvarItemPedido(itemPedido, pedidoId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		return itemPedido;
	}
	
}
	
	

