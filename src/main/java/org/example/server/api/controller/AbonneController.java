package org.example.server.api.controller;

import java.util.Collection;

import org.example.server.api.service.AbonneService;
import org.example.server.api.service.ContratService;
import org.example.server.api.service.MouvementService;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.example.server.model.Mouvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AbonneController {

	@Autowired
	private AbonneService service;


	@GetMapping("/abonnes")
	public Iterable<Abonne> getAllAbonnes() {
		return service.listAll();
	}

	@GetMapping("/abonnes/{id}")
	public Abonne getOneAbonne(@PathVariable("id") String id) {
		return service.get(Long.parseLong(id));
	}

	@PostMapping("/abonnes")
	public Abonne addAllAbonne(@RequestBody Abonne requestabonne) {
		Abonne result;
		try {
			result = service.save(requestabonne);
		} catch (final Throwable e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Erreure sur l'ajout de l'abonné : " + requestabonne.getNom(), e);
		}
		return result;
	}

	@PutMapping("/abonnes/{id}")
	public Abonne update(@PathVariable("id") long id, @RequestBody Abonne requestabonne) {

		return service.update(id, requestabonne);
	}

	@DeleteMapping("/abonnes/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
}
