package com.book.api.repositorio;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.book.api.modelo.Livro;

@Component
public class SimuladorDeBuscaNoBanco{
	
	//Simula uma busca no banco de dados que demora 5 segundos.
	@Cacheable("livros")
	public Livro buscaLivrosPorId(long id) {
		simularServicoLento();
		return new Livro(id, "Livro " + id);
	}
	
	private void simularServicoLento() {
		try {
		      long time = 10000L;
		      Thread.sleep(time);
		    } catch (InterruptedException e) {
		      throw new IllegalStateException(e);
		    }
	}
	

}
