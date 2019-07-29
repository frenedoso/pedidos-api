package com.rest.pedidosapi.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String Nome;
	
	private BigDecimal Preco;
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public BigDecimal getPreco() {
		return Preco;
	}

	public void setPreco(BigDecimal preco) {
		Preco = preco;
	}

	public Integer getMultiplo() {
		return Multiplo;
	}

	public void setMultiplo(Integer multiplo) {
		Multiplo = multiplo;
	}

	public long getId() {
		return id;
	}

	private Integer Multiplo;
}
