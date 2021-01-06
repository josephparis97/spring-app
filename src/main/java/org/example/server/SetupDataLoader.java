package org.example.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.example.server.api.repository.AbonneRepository;
import org.example.server.api.repository.ContratRepository;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private ContratRepository contratRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		addContrat("68 rue de la liberté 70510 Remiro");
		addAbonne("Joseph", "Paris", "adresse", Arrays.asList(1L));
	}

	private void addContrat(String string) {
		final Contrat contrat = new Contrat();
		contrat.setAdresse(string);
		contratRepository.save(contrat);
	}

	private void addAbonne(String prenom, String nom, String adresse, List<Long> contratids) {
		final Abonne abonne = new Abonne();
		abonne.setPrenom(prenom);
		abonne.setNom(nom);
		abonne.setAdresse(adresse);

		final List<Contrat> contrats = new ArrayList<>();
		contratids.stream().forEach(id -> contratRepository.findById(id).ifPresent(contrats::add));
		abonne.setContrats(contrats);
		abonneRepository.save(abonne);
	}

}