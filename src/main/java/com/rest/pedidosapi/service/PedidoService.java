package com.rest.pedidosapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pedidosapi.model.Cliente;
import com.rest.pedidosapi.model.ItemPedido;
import com.rest.pedidosapi.model.Pedido;
import com.rest.pedidosapi.model.Produto;
import com.rest.pedidosapi.repository.ItemPedidoRepository;
import com.rest.pedidosapi.repository.PedidoRepository;
import com.rest.pedidosapi.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido salvarPedido(Pedido pedido) throws Exception {
		
		Cliente cliente = clienteService.getClienteById(pedido.getCliente().getId()); 

		pedido.setCliente(cliente);
		pedidoRepository.save(pedido);
		return pedido;
	}

	public List<ItemPedido> getPedidoItens(long pedidoId) {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if (!pedido.isPresent())
			throw new RuntimeException("Pedido não encontrado");
		
		return pedido.get().getItens();
		
	}

	public ItemPedido salvarItemPedido(@Valid ItemPedido itemPedido, long pedidoId) throws Exception {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if (!pedido.isPresent())
			throw new RuntimeException("Pedido não encontrado");
		
		Optional<Produto> produto = produtoRepository.findById(itemPedido.getProduto().getId());
		if (!produto.isPresent())
			throw new RuntimeException("Produto não encontrado");
		
		itemPedido.setProduto(produto.get());		
		itemPedido.setPedido_id(pedidoId);
		
		validarItemPedido(itemPedido);
		
		itemPedidoRepository.save(itemPedido);
		return itemPedido;
		
	}

	private void validarItemPedido(ItemPedido itemPedido) throws Exception {
		validarQuantidade(itemPedido);
		validarMultiplo(itemPedido);
		validarRentabilidade(itemPedido);		
	}

	private void validarQuantidade(ItemPedido itemPedido) throws Exception {
		if (itemPedido.getQuantidade() <= 0) {
			throw new Exception("Quantidade deve ser maior que zero");
		}			
	}

	private void validarMultiplo(ItemPedido itemPedido) throws Exception {
		Integer multiplo = itemPedido.getProduto().getMultiplo();
		
		if ((multiplo != null) && (multiplo > 0)) {
			if ((itemPedido.getQuantidade() % multiplo) != 0) {
				throw new Exception(String.format("Quantidade deve ser múltipla de %s", multiplo));
			}
		}				
	}

	private void validarRentabilidade(ItemPedido itemPedido) throws Exception {
		BigDecimal precoProduto = itemPedido.getProduto().getPreco();
		BigDecimal menorPreco = precoProduto.multiply(BigDecimal.valueOf(0.9));
		BigDecimal precoPedido = itemPedido.getPrecoUnitario();
		
		if (menorPreco.compareTo(precoPedido) >= 0 ) {
			throw new Exception(String.format("Rentabilidade Ruim. Preço unitário deve ser maior que %.2f", menorPreco.doubleValue()));
		}
	}

	public Pedido getPedido(long pedidoId) throws Exception {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		
		if (!pedido.isPresent())
			throw new Exception("Pedido não encontrado"); 
		return pedido.get();
	}
}
