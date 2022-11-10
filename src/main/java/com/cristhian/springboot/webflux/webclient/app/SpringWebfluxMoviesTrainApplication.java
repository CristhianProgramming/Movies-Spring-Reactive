package com.cristhian.springboot.webflux.webclient.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cristhian.springboot.webflux.webclient.app.models.Categoria;
import com.cristhian.springboot.webflux.webclient.app.service.IServiceCategoria;
import com.cristhian.springboot.webflux.webclient.app.service.IServiceMovies;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebfluxMoviesTrainApplication implements CommandLineRunner {

	@Autowired
	IServiceMovies serviceMovie;
	
	@Autowired 
	IServiceCategoria serviceCategoria;
	
	@Autowired
	private static Logger logge = LoggerFactory.getLogger(SpringWebfluxMoviesTrainApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxMoviesTrainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Flux CategoriasInsert = Flux.just(new Categoria("Accion"),new Categoria("Romance"),new Categoria("Futurista")).flatMap(p->{
			return serviceCategoria.saveCategoria(p);
		}).doOnNext(fp->logge.info("Se inserto la categoria "+fp.getNombre()));
		
		CategoriasInsert.subscribe();
		
	}
	


}
