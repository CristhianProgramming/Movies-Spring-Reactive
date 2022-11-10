package com.cristhian.springboot.webflux.webclient.app.service;

import com.cristhian.springboot.webflux.webclient.app.models.Categoria;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceCategoria {
	
	public Flux<Categoria> findAllCategorias();
	
	public Mono<Categoria> findByIdCategoria(String idCategoria);
	
	public Mono<Categoria> saveCategoria(Categoria categoria);
}
