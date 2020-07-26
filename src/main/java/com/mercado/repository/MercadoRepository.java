package com.mercado.repository;

import com.mercado.model.Mercadinho;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MercadoRepository extends JpaRepository<Mercadinho, Long>{

	@Query("SELECT m FROM Mercadinho m WHERE m.produto like %?1%")
	List<Mercadinho> findByNomeProduto(String produto);
	
	default Page<Mercadinho> findByNamePage(String produto, PageRequest pageRequest){
		Mercadinho mercadinho = new Mercadinho();
		
		mercadinho.setProduto(produto);
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("produto",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
				
		Example<Mercadinho> example = Example.of(mercadinho, exampleMatcher);
		
		Page<Mercadinho> retorno = findAll(example, pageRequest);
		
		return retorno;
	}
	
}
