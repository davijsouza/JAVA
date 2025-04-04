package com.book.api.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.api.modelo.Livro;
import com.book.api.repositorio.SimuladorDeBuscaNoBanco;

@RestController
@RequestMapping("api")
public class LivroControlador {
	
	@Autowired
	SimuladorDeBuscaNoBanco repositorio;
	
	@GetMapping("buscar/id/{id}")
	public Livro buscarPorId(
			@PathVariable(name = "id") long id) {
		return repositorio.buscaLivrosPorId(id);
	}

}
