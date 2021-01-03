package org.example.server.api.controller;


import org.example.server.api.service.ContratService;

import org.example.server.model.Contrat;
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
public class ContratController {
	
	@Autowired
    private ContratService service;

    @GetMapping("/contrats")
    public Iterable<Contrat> getAllContrats() {
	return service.listAll();
    }

    @GetMapping("/contrats/{id}")
    public Contrat getOneContrat(@PathVariable("id") String id) {
	return service.get(Long.parseLong(id));
    }

    @PostMapping("/contrats")
    public Contrat addAllAbonne(@RequestBody Contrat requestContrat) {
	Contrat result;
	try {
	    result = service.save(requestContrat);
	} catch (final Throwable e) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
		    "Erreur sur l'ajout du contrat : " + requestContrat.getId(), e);
	}
	return result;
    }

    @PutMapping("/contrats/{id}")
    public Contrat update(@PathVariable("id") long id, @RequestBody Contrat requestContrat) {
	return service.update(id, requestContrat);
    }

    @DeleteMapping("/contrats/{id}")
    public void delete(@PathVariable("id") Long id) {
	service.delete(id);
    }
}
