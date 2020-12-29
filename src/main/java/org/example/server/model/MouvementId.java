package org.example.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MouvementId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8358350028677024550L;

	@Column(name="abonne_id")
	private Long abonneId;
	
	@Column(name="contrat_id")
	private Long contratId;

	public MouvementId(Long abonneId, Long contratId) {
		super();
		this.abonneId = abonneId;
		this.contratId = contratId;
	}
	
	public MouvementId() {}

	public Long getAbonneId() {
		return abonneId;
	}

	public void setAbonneId(Long abonneId) {
		this.abonneId = abonneId;
	}

	public Long getContratId() {
		return contratId;
	}

	public void setContratId(Long contratId) {
		this.contratId = contratId;
	}
	
	
	
	
}
