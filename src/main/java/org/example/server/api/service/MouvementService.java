package org.example.server.api.service;


import java.util.stream.StreamSupport;

import org.example.server.api.repository.MouvementRepository;
import org.example.server.model.Mouvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MouvementService {

	@Autowired
	private MouvementRepository repo;

	public Iterable<Mouvement> listAll() {
		return repo.findAll();
	}
	

	public long count() {
		return repo.count();
	}

	public Mouvement save(Mouvement product) {
		return repo.save(product);
	}

	public Mouvement get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
	/*
	 * public Mouvement update(Long id, Mouvement product) { product.setId(id);
	 * return repo.save(product); }
	 */


	public Iterable<Mouvement> listAllByAbonneid(String id) {
		 
		 Iterable<Mouvement> mouvements=() -> StreamSupport.stream(repo.findAll().spliterator(), false)
		        .filter(mouvement -> mouvement.getAbonne().getId()==Long.parseLong(id))
		        .iterator();
		
		
		
		return mouvements;
	}
}
