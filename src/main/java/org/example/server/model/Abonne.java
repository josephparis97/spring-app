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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



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
	//@JsonIgnoreProperties("users")
	private Collection<Contrat> contrats;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Collection<Contrat> getContrats() {
		return contrats;
	}


	public void setContrats(Collection<Contrat> contrats) {
		this.contrats = contrats;
	}
	
	
	
}
