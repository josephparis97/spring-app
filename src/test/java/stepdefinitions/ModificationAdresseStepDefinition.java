package stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.example.server.model.Mouvement;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModificationAdresseStepDefinition {

	private RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:8080/";
	Collection<Contrat> contrats = new ArrayList<Contrat>();
	Contrat contrat = new Contrat(1L, "68 rue de la liberté 70510 Remiro");
	
	@Given("un abonné avec une adresse principale en France")
	public void un_abonne_avec_une_adresse_principale_en_france() {
		contrats.add(contrat);
		Abonne abonne = new Abonne(0L, "Roger", "Rabbit", "68 rue de la liberté 70510 Remiro", contrats);
		Abonne reponseAbonne = restTemplate.postForObject(baseUrl+"/abonnes", abonne, Abonne.class);
		assertNotNull(reponseAbonne);
		System.out.println(reponseAbonne.toString());
	}

	@When("le conseiller modifie l adresse de l abonné")
	public void le_conseiller_modifie_l_adresse_de_l_abonne() {
		Abonne abonneModifie=new Abonne(2L, "Roger", "Rabbit", "68 rue de la solitude 70510 Remiro", contrats);
		restTemplate.put(baseUrl+"/abonnes/2", abonneModifie);
		Abonne verificationAbonne=restTemplate.getForObject(baseUrl+"/abonnes/2", Abonne.class);
		System.out.println("adresse :"+verificationAbonne.getAdresse());
		assertEquals(verificationAbonne.getAdresse(), "68 rue de la solitude 70510 Remiro");
	}

	@Then("la nouvelle adresse de l abonné est enregistrée sur l ensemble des contrats de l abonné")
	public void la_nouvelle_adresse_de_l_abonne_est_enregistrée_sur_l_ensemble_des_contrats_de_l_abonne() {
		Abonne abonne=restTemplate.getForObject(baseUrl+"/abonnes/2", Abonne.class);
		Collection<Contrat> contrats=abonne.getContrats();
		
		for (Contrat contrat : contrats) {
			assertEquals("68 rue de la solitude 70510 Remiro",contrat.getAdresse());
		}
	}

	@Then("un mouvement de modification d adresse est créé avec la nouvelle adresse")
	public void un_mouvement_de_modification_d_adresse_est_cree_avec_la_nouvelle_adresse() {
		Collection<Mouvement> mouvements=restTemplate.exchange(baseUrl+ "/mouvements", HttpMethod.GET, null,new ParameterizedTypeReference<List<Mouvement>>() {}).getBody();
		Mouvement derniermouvement=mouvements.stream().filter(mouvement->mouvement.getAbonne().getId().equals(2L)).findFirst().get();
		assertEquals("68 rue de la solitude 70510 Remiro", derniermouvement.getNouvelleValeur());
	}

}
