package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CompteEpargne extends Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float tauxInteret;
	

	public CompteEpargne() {
	}

	public float getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	
	
	
	

}
