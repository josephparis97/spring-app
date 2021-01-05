package org.example.server.api.service;

import java.util.Collection;

import org.example.server.api.repository.AbonneRepository;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.example.server.model.Mouvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbonneService {

	@Autowired
	private AbonneRepository repo;
	
	@Autowired
	private ContratService serviceContrat;
	
	@Autowired
	private MouvementService mouvementService;

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
		if (product.getAdresse() != get(id).getAdresse()) {
		String ancienneAdresse=get(id).getAdresse();
		String nouvelleAdresse = product.getAdresse();
		Collection<Contrat> contrats = product.getContrats();
		contrats.stream().forEach(contrat -> {
			contrat.setAdresse(nouvelleAdresse);
			serviceContrat.update(contrat.getId(), contrat);
			Mouvement mouvement=new Mouvement(product, contrat, "modification d'adresse", ancienneAdresse, nouvelleAdresse);
			mouvementService.save(mouvement);
		});	
		}
		return repo.save(product);
	}
}
