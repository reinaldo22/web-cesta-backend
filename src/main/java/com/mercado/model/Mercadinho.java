package com.mercado.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mercadinho {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String produto;
	private double preco;
	private String colaborador;
	private String nomeMercado;


	public Mercadinho(Long id, String produto, double preco, String colaborador, String nomeMercado) {
		super();
		this.id = id;
		this.produto = produto;
		this.preco = preco;
		this.colaborador = colaborador;
		this.nomeMercado = nomeMercado;
	}

	public double getTotal() {

		double soma = 0.0;
		soma = soma + getPreco();

		return soma;
	}

	public Mercadinho() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {

		return preco;
	}

	public void setPreco(double preco) {

		this.preco = preco;
	}

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public String getNomeMercado() {
		return nomeMercado;
	}

	public void setNomeMercado(String nomeMercado) {
		this.nomeMercado = nomeMercado;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mercadinho other = (Mercadinho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
