package org.example.server.api.controller;

import java.util.Collection;

import org.example.server.api.service.ContratService;
import org.example.server.api.service.MouvementService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MouvementController {

	@Autowired
    private MouvementService service;

    @GetMapping("/mouvements")
    public Iterable<Mouvement> getAllMouvements() {
	return service.listAll();
    }
    
    @GetMapping("/mouvements/abonnes/{id}")
    public Iterable<Mouvement> getAllMouvementsByAbonne(@PathVariable("id") String id) {
    	return service.listAllByAbonneid(id);
    }

    @GetMapping("/mouvements/{id}")
    public Mouvement getOneMouvement(@PathVariable("id") String id) {
	return service.get(Long.parseLong(id));
    }

    @PostMapping("/mouvements")
    public Mouvement addAllMouvements(@RequestBody Mouvement requestMouvement) {
	Mouvement result;
	try {
	    result = service.save(requestMouvement);
	} catch (final Throwable e) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
		    "Erreur sur l'ajout du mouvement :" + requestMouvement.getId(), e);
	}
	return result;
    }

//    @PutMapping("/mouvements/{id}")
//    public Contrat update(@PathVariable("id") long id, @RequestBody Mouvement requestMouvement) {
//	return service.update(id, requestMouvement);
//    }

    @DeleteMapping("/mouvements/{id}")
    public void delete(@PathVariable("id") Long id) {
	service.delete(id);
    }
}
