package org.example.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.example.server.api.repository.AbonneRepository;
import org.example.server.api.repository.ContratRepository;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SetupDataLoader.class);

	boolean alreadySetup = false;

	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private ContratRepository contratRepository;

	@Override
    @Transactional
    @SuppressWarnings("java:S1192")
    public void onApplicationEvent(ContextRefreshedEvent event) {
	//createRoleIfNotFound("ROLE_ADMIN");
	//createDepartmentIfNotFound("SET", "SET Department");
	addAbonne("Joseph", "Paris", "adresse", Arrays.asList(1L));
	//addContrat("J", "Agent", "0679777194", "j@mib.com", "MIB");
	//addMouvement("Drone");
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