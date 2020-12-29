package org.example.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;



@Entity
public class Mouvement {
	
	@EmbeddedId
    private MouvementId id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("abonneId")
    private Abonne abonne;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("contratId")
    private Contrat contrat;
 
    @Column(name = "creation")
    private Date creation= new Date();
    
    private String typeDeModification;
    
    private String ancienneValeur;
    
    private String nouvelleValeur;
 
  
 
    public Mouvement(Abonne abonne, Contrat contrat,String typeDeModification,String ancienneValeur, String nouvelleValeur) {
        this.contrat = contrat;
        this.abonne = abonne;
        this.typeDeModification=typeDeModification;
        this.ancienneValeur=ancienneValeur;
        this.nouvelleValeur=nouvelleValeur;
        this.id = new MouvementId(abonne.getId(), contrat.getId());
    }
    public Mouvement() {}



	public MouvementId getId() {
		return id;
	}



	public void setId(MouvementId id) {
		this.id = id;
	}



	public Abonne getAbonne() {
		return abonne;
	}



	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}



	public Contrat getContrat() {
		return contrat;
	}



	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}



	public Date getCreation() {
		return creation;
	}



	public void setCreation(Date creation) {
		this.creation = creation;
	}



	public String getTypeDeModification() {
		return typeDeModification;
	}



	public void setTypeDeModification(String typeDeModification) {
		this.typeDeModification = typeDeModification;
	}



	public String getAncienneValeur() {
		return ancienneValeur;
	}



	public void setAncienneValeur(String ancienneValeur) {
		this.ancienneValeur = ancienneValeur;
	}



	public String getNouvelleValeur() {
		return nouvelleValeur;
	}



	public void setNouvelleValeur(String nouvelleValeur) {
		this.nouvelleValeur = nouvelleValeur;
	}
    
    
    
}
