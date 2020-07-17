package com.mercado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mercado.model.Usuario;
import com.mercado.repository.UsuarioRepository;


/*Classe do spring security que representa o usuário logado, com permissoes de acessos dentre outras*/
@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(username);
	
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return usuario;
	}

}
