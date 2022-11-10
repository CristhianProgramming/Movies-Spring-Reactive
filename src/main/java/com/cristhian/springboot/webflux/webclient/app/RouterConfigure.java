package com.cristhian.springboot.webflux.webclient.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cristhian.springboot.webflux.webclient.app.handler.MoviesHandler;

@Configuration
public class RouterConfigure {

	@Autowired
	MoviesHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> routes(){
		return RouterFunctions.route(RequestPredicates.GET("/api/v1/movies"),handler::listar)
				.andRoute(RequestPredicates.GET("/api/v1/movie/{id}"), handler::ver)
				.andRoute(RequestPredicates.POST("/api/v1/movie"), handler::save)
				.andRoute(RequestPredicates.PUT("/api/v1/movie/{id}"), handler::update)
				.andRoute(RequestPredicates.DELETE("/api/v1/movie/{id}"), handler::delete)
				;
	}
	
}
