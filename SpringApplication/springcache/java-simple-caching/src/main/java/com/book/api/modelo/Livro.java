package com.book.api.modelo;

public class Livro {
	
	private long id;
	private String nome;
	
	public Livro(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}
}


