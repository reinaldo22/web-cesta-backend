package com.mercado.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.model.Mercadinho;
import com.mercado.repository.MercadoRepository;
import com.mercado.service.RelatorioService;
import org.apache.tomcat.util.codec.binary.Base64;

@RestController
@RequestMapping(value = "/mercado")
public class MercadoController {

	@Autowired
	private MercadoRepository mercadoRepository;
	
	@Autowired
	private RelatorioService relatorioService;

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Mercadinho> cadastrar(@RequestBody Mercadinho mercadinho) {

		Mercadinho mercd = mercadoRepository.save(mercadinho);

		return new ResponseEntity<Mercadinho>(mercd, HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<List<Mercadinho>> pegaMercadinho() {
		
		List<Mercadinho> listaMerc = mercadoRepository.findAll();

		return new ResponseEntity<List<Mercadinho>>(listaMerc, HttpStatus.OK);
	}

	@GetMapping(value = "/buscaNome/{produto}", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Page<Mercadinho>> buscaNome(@PathVariable("produto") String produto)
			throws InterruptedException {
		PageRequest pageRequest = null;
		Page<Mercadinho> list = null;

		if (produto == null || (produto != null && produto.trim().isEmpty()) || produto.equalsIgnoreCase("undefined")) {
			pageRequest = PageRequest.of(0, 5, Sort.by("produto"));
			list = mercadoRepository.findAll(pageRequest);
		} else {
			pageRequest = PageRequest.of(0, 5, Sort.by("produto"));
			list = mercadoRepository.findByNamePage(produto, pageRequest);
		}
		return new ResponseEntity<Page<Mercadinho>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/page/{pagina}", produces="application/json")
	public ResponseEntity<Page<Mercadinho>> mercadoPage(@PathVariable("pagina") int pagina)
			throws InterruptedException {
		PageRequest page = PageRequest.of(pagina, 5, Sort.by("produto"));

		Page<Mercadinho> list = mercadoRepository.findAll(page);

		return new ResponseEntity<Page<Mercadinho>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/relatorio", produces = "application/text")
	public ResponseEntity<String> donwloadRelatorio(HttpServletRequest request) throws Exception {

		byte[] pdf = relatorioService.gerarRelatorio("documento", request.getServletContext());

		String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);

		return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = "application/text")

	public String deletarMercadinho(@PathVariable("id") Long id) {

		mercadoRepository.deleteById(id);

		return "EXCLUÍDO COM SUCESSO!";
	}

}
