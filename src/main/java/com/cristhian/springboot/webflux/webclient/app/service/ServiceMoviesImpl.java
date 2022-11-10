package com.cristhian.springboot.webflux.webclient.app.service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhian.springboot.webflux.webclient.app.dao.IMoviesDao;
import com.cristhian.springboot.webflux.webclient.app.models.Movies;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ServiceMoviesImpl implements IServiceMovies {

	@Autowired
	IMoviesDao moviesDaos;
	
	@Override
	public Flux<Movies> findAllMovies() {
		return moviesDaos.findAll();
	}

	@Override
	public Mono<Movies> findByIdMovie(String idMovies) {
		return moviesDaos.findById(idMovies);
	}

	@Override
	public Mono<Movies> saveMovie(Movies movie) {
		return moviesDaos.save(movie).flatMap(p->{
			if (p.getFechaCreacion() == null) {
				p.setFechaCreacion(new Date());
			}
			return moviesDaos.save(p);
		});
	}

	@Override
	public Mono<Void> deleteMovie(String idMovie) {
		return moviesDaos.deleteById(idMovie);
	}

	@Override
	public Mono<Movies> updateMovie(Movies movie) {
		return moviesDaos.save(movie).flatMap(p->{
			p.setFechaModificacion(new Date());		
			return moviesDaos.save(p);
		});
	}

}
