package com.mercado.repository;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.domain.Example;
import com.mercado.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.login = ?1")
	Usuario findByLogin(String nome);

	@Query("SELECT u FROM Usuario u WHERE u.nome like %?1%")
	List<Usuario> findUserByNome(String username);

	default Page<Usuario> findUserByNamePage(String nome, PageRequest pageRequest){
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome", ExampleMatcher.GenericPropertyMatchers
				.contains().ignoreCase());
		
		Example<Usuario> example = Example.of(usuario, exampleMatcher);
		
		Page<Usuario> retorno = findAll(example, pageRequest);
		
		return retorno;
	}

}
