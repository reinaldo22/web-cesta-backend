package com.mercado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercado.model.Usuario;
import com.mercado.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario) {

		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}


}
