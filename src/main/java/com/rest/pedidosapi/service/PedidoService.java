package com.rest.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pedidosapi.model.Cliente;
import com.rest.pedidosapi.model.ItemPedido;
import com.rest.pedidosapi.model.Pedido;
import com.rest.pedidosapi.repository.PedidoRepository;

@Service
public class PedidoService {
	
	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido salvaPedido(Pedido pedido) throws RuntimeException {
		
		Optional<Cliente> cliente = clienteService.getClienteById(pedido.getCliente().getId()); 
		if (!cliente.isPresent()) {
			throw new RuntimeException(CLIENTE_NAO_ENCONTRADO);
		}
		pedido.setCliente(cliente.get());
		pedidoRepository.save(pedido);
		return pedido;
	}

	public List<ItemPedido> getPedidoItens(long pedidoId) {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if (!pedido.isPresent())
			throw new RuntimeException("Pedido não encontrado");
		
		return pedido.get().getItens();
		
	}
}
