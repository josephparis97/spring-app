package mocked;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.server.api.controller.AbonneController;
import org.example.server.api.controller.MouvementController;
import org.example.server.api.service.AbonneService;
import org.example.server.api.service.MouvementService;
import org.example.server.model.Abonne;
import org.example.server.model.Contrat;
import org.example.server.model.Mouvement;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ModificationAdresseMockTest extends AbstractTestNGSpringContextTests {
    
    private MockMvc mvc;
    
    @Mock
    private AbonneService abonneService; 
  
    @InjectMocks
    private AbonneController abonneController;
    
    @InjectMocks
    private MouvementController mouvementController;
    
    @Mock 
    private MouvementService mouvementService;
    
    @SuppressWarnings("deprecation")
	@BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc=MockMvcBuilders.standaloneSetup(abonneController).build();
    }

    private ObjectMapper mapper = new ObjectMapper();
  
    @Test
    public void un_abonné_avec_une_adresse_principale_en_france() throws Exception {
    	Collection<Contrat> contrats=Stream.of(new Contrat(1L, "11 rue des volontaires")).collect(Collectors.toCollection(ArrayList::new));
    	Abonne abonne = new Abonne(0L, "Roger", "Rabbit", "11 rue des volontaires", contrats);
        Mockito.when(abonneService.save(Mockito.any(Abonne.class))).thenReturn(abonne);
        mvc.perform(post("/abonnes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(abonne))
        ).andExpect(status().isOk()).andReturn();
    }

	@Test
	public void le_conseiller_modifie_l_adresse_de_l_abonné() throws Exception {
    	Collection<Contrat> contrats=Stream.of(new Contrat(1L, "11 rue des Espadrilles")).collect(Collectors.toCollection(ArrayList::new));
    	Abonne abonneModifié = new Abonne(0L, "Roger", "Rabbit", "11 rue des Espadrilles", contrats);
		Mockito.when(abonneService.update(Mockito.anyLong(), Mockito.any(Abonne.class))).thenReturn(abonneModifié);
		mvc.perform(put("/abonnes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(abonneModifié))
        ).andExpect(status().isOk());
	}

	@Test
	public void la_nouvelle_adresse_de_l_abonné_est_enregistrée_sur_l_ensemble_des_contrats_de_l_abonné() throws Exception {
    	Collection<Contrat> contrats=Stream.of(new Contrat(1L, "11 rue des Espadrilles")).collect(Collectors.toCollection(ArrayList::new));
    	Abonne abonne = new Abonne(0L, "Roger", "Rabbit", "11 rue des Espadrilles", contrats);
		Mockito.when(abonneService.get(Mockito.anyLong())).thenReturn(abonne);	
		mvc.perform(get("/abonnes/1")).andExpect(jsonPath("$.contrats[0].adresse", is("11 rue des Espadrilles"))).andExpect(status().isOk());
	}

	@Test
	public void un_mouvement_de_modification_d_adresse_est_créé_avec_la_nouvelle_adresse() throws Exception {
		mvc=MockMvcBuilders.standaloneSetup(mouvementController).build();
    	Collection<Contrat> contrats=Stream.of(new Contrat(1L, "11 rue des Espadrilles")).collect(Collectors.toCollection(ArrayList::new));
    	Mouvement mouvement= new Mouvement(new Abonne(1L, "Roger", "Rabbit", "11 rue des Espadrilles", contrats), new Contrat(1L,"11 rue des Espadrilles"), "modification d'adresse", "11 rue des volontaires", "11 rue des Espadrilles");
		Iterable<Mouvement> mouvements=Stream.of(mouvement).collect(Collectors.toList());
    	Mockito.when(mouvementService.listAllByAbonneid("1")).thenReturn(mouvements);
		System.out.println("Result"+mvc.perform(get("/mouvements/abonnes/1")).andReturn().getResponse().getContentAsString()); 
		mvc.perform(get("/mouvements/abonnes/1")).andExpect((jsonPath("$[0].abonne.adresse", is("11 rue des Espadrilles"))));
	}
}
