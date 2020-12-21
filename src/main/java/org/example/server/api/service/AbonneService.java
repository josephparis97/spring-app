package org.example.server.api.service;

import org.example.server.api.repository.AbonneRepository;
import org.example.server.model.Abonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class AbonneService {

	@Autowired
	private AbonneRepository repo;

	public Iterable<Abonne> listAll() {
		return repo.findAll();
	}

	public long count() {
		return repo.count();
	}

	public Abonne save(Abonne product) {
		return repo.save(product);
	}

	public Abonne get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Abonne update(Long id, Abonne product) {
		product.setId(id);
		return repo.save(product);
	}
}
