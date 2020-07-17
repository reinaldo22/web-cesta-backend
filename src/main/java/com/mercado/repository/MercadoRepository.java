package com.mercado.repository;

import com.mercado.model.Mercadinho;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoRepository extends JpaRepository<Mercadinho, Long>{

	
}
