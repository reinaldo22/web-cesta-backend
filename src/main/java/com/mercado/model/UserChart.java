package com.mercado.model;

public class UserChart {
	private String preco;
	private String produto;

	public UserChart(String preco, String produto) {
		super();
		this.preco = preco;
		this.produto = produto;
	}

	public UserChart() {
		super();
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

}
