package com.cristhian.springboot.webflux.webclient.app.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cristhian.springboot.webflux.webclient.app.models.Movies;
import com.cristhian.springboot.webflux.webclient.app.service.IServiceMovies;

import reactor.core.publisher.Mono;

@Component
public class MoviesHandler {

	@Autowired
	IServiceMovies serviceMovies;

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(serviceMovies.findAllMovies(),
				Movies.class);
	}

	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(serviceMovies.findByIdMovie(id),
				Movies.class);
	}

	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<Movies> movie = request.bodyToMono(Movies.class);
		return movie.flatMap(p -> {
			return serviceMovies.saveMovie(p).flatMap(pr -> {
				return ServerResponse.created(URI.create("/api/v1/movie".concat(pr.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(pr));
			});

		});
	}

	public Mono<ServerResponse> update(ServerRequest request) {

		Mono<Movies> movie = request.bodyToMono(Movies.class);
		String id = request.pathVariable("id");
		Mono<Movies> moviedb = serviceMovies.findByIdMovie(id);

		return moviedb.zipWith(movie, (db, mv) -> {

			db.setNombre((mv.getNombre() != null) ? mv.getNombre() : db.getNombre());
			db.setCategoria((mv.getCategoria() != null) ? mv.getCategoria() : db.getCategoria());

			return db;
		}).flatMap(p -> {
			return ServerResponse.created(URI.create("/api/v1/movie".concat(p.getId())))
					.contentType(MediaType.APPLICATION_JSON).body(serviceMovies.updateMovie(p), Movies.class);
		});

	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");
		return serviceMovies.findByIdMovie(id).flatMap(pr -> {
			return serviceMovies.deleteMovie(pr.getId()).flatMap(p -> {
				return ServerResponse.ok().build();
			});
		});
	}
}
