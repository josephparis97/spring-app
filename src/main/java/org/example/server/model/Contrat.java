package org.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contrat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String adresse;
	
	public Contrat(Long id, String adresse) {
		super();
		this.id = id;
		this.adresse = adresse;
	}

	public Contrat() {
	}
	
}
