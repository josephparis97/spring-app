package org.example.server.api.service;

import org.example.server.api.repository.AbonneRepository;
import org.example.server.api.repository.ContratRepository;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContratService {

	@Autowired
	private ContratRepository repo;

	public Iterable<Contrat> listAll() {
		return repo.findAll();
	}

	public long count() {
		return repo.count();
	}

	public Contrat save(Contrat product) {
		return repo.save(product);
	}

	public Contrat get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Contrat update(Long id, Contrat product) {
		product.setId(id);
		return repo.save(product);
	}
}
