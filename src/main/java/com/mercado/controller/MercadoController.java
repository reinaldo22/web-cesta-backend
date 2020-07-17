package com.mercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mercado.model.Mercadinho;
import com.mercado.repository.MercadoRepository;

@RestController
@RequestMapping(value = "/mercado")
public class MercadoController {
	
	@Autowired
	MercadoRepository mercadoRepository;
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Mercadinho> cadastrar(@RequestBody Mercadinho mercadinho){
		
		Mercadinho mercd = mercadoRepository.save(mercadinho);
		
		return new ResponseEntity<Mercadinho>(mercd, HttpStatus.OK);
	}
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Mercadinho>>pegaMercadinho(){
		List<Mercadinho> listaMerc = mercadoRepository.findAll();
		
		return new ResponseEntity<List<Mercadinho>>(listaMerc, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}", produces = "application/text")
	@Secured({"ROLE_ADMIN"})
	public String deletarMercadinho(@PathVariable("id") Long id) {
		
		
		mercadoRepository.deleteById(id);
		
		
		
		return "EXCLUÍDO COM SUCESSO!";
	}
	

}
