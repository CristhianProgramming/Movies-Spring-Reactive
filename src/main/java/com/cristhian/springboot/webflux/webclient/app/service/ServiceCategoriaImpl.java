package com.cristhian.springboot.webflux.webclient.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhian.springboot.webflux.webclient.app.dao.ICategoriaDao;
import com.cristhian.springboot.webflux.webclient.app.models.Categoria;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceCategoriaImpl implements IServiceCategoria {
		
	@Autowired
	ICategoriaDao categoriaDao;
	
	@Override
	public Flux<Categoria> findAllCategorias() {
		return categoriaDao.findAll();
	}

	@Override
	public Mono<Categoria> findByIdCategoria(String idCategoria) {
		return categoriaDao.findById(idCategoria);
	}

	@Override
	public Mono<Categoria> saveCategoria(Categoria categoria) {
		return categoriaDao.save(categoria);
	}

}
