package org.example.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;


@Data
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
 
}
