package com.cristhian.springboot.webflux.webclient.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.cristhian.springboot.webflux.webclient.app.models.Categoria;

@Repository
public interface ICategoriaDao extends ReactiveMongoRepository<Categoria, String> {

}
