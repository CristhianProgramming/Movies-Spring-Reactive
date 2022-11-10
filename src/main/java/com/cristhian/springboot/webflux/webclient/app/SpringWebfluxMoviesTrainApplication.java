package com.cristhian.springboot.webflux.webclient.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.cristhian.springboot.webflux.webclient.app.models.Categoria;
import com.cristhian.springboot.webflux.webclient.app.models.Movies;
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
	ReactiveMongoTemplate mongotemplate;
	
	
	@Autowired
	private static Logger logge = LoggerFactory.getLogger(SpringWebfluxMoviesTrainApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxMoviesTrainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongotemplate.dropCollection("movies").subscribe();
		mongotemplate.dropCollection("categoria").subscribe();

		Categoria accion = new Categoria("Accion");

		Flux.just(accion, new Categoria("Romance"), new Categoria("Futurista"))
		.flatMap( serviceCategoria::saveCategoria)
		.doOnNext(fp ->
		{ logge.info("Se inserto la categoria " + fp.getNombre());
		}).thenMany(
				Flux.just(new Movies("Matrix", accion), new Movies("Rambo el Regreso", accion),new Movies("Avatar en Cine", accion))
				.flatMap(pl -> {
					return serviceMovie.saveMovie(pl);
				})
		).subscribe();
	
	}
	


}
