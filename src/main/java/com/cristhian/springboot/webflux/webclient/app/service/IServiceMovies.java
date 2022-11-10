package com.cristhian.springboot.webflux.webclient.app.service;

import com.cristhian.springboot.webflux.webclient.app.models.Movies;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceMovies {

	public Flux<Movies> findAllMovies(); 
	
	public Mono<Movies> findByIdMovie(String idMovies);
	
	public Mono<Movies> saveMovie(Movies movie);
	
	//public Mono<Movies> updateMovie(Movies movie,String idMovie);
	
	public Mono<Void> deleteMovie(String idMovie);
	
}
