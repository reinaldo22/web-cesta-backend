package com.mercado.model;

import java.util.ArrayList;
import java.util.List;

public class Contabilidade {

	private List<Mercadinho> mercados = new ArrayList<Mercadinho>();

	public List<Mercadinho> getMercados() {
		return mercados;
	}

	public void setMercados(List<Mercadinho> mercados) {
		this.mercados = mercados;
	}


	
}
