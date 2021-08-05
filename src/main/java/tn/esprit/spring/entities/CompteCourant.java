package tn.esprit.spring.entities;

import java.io.Serializable;


import javax.persistence.Entity;


@Entity
public class CompteCourant extends Compte implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double decouvert=(soldeCompte*(-0.25));




	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}





	public CompteCourant(double decouvert) {
		super();
		this.decouvert = decouvert;
	}


	


	public double getDecouvert() {
		return soldeCompte*(-0.25);
	}


	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	

	}

	
	


