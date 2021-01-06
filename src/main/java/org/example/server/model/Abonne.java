package org.example.server.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
public class Abonne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "prenom", nullable = false)
	private String prenom;

	@NotNull
	@Column(name = "nom", nullable = false)
	private String nom;

	@NotNull
	@Column(name = "adresse", nullable = false)
	private String adresse;
	
	@ManyToMany
	@JoinTable(name = "abonne_contrat", joinColumns = @JoinColumn(name = "abonne_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "contrat_id", referencedColumnName = "id"))
	private Collection<Contrat> contrats;

	public Abonne(Long id, @NotNull String prenom, @NotNull String nom, @NotNull String adresse,
			Collection<Contrat> contrats) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.contrats = contrats;
	}

	public Abonne() {
	}
	
}
