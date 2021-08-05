package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class EcheancePaiment implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEcheance;
	private LocalDate date;
	private double montant;
	
    public double getMontant() {
		return montant;
	}



	public void setMontant(double montant) {
		this.montant = montant;
	}

	@ManyToOne
	@JsonIgnore
    private Credit credit;

    
    

    public EcheancePaiment() {
		super();
		// TODO Auto-generated constructor stub
	}



    public Long getIdEcheance() {
		return idEcheance;
	}



	public void setIdEcheance(Long idEcheance) {
		this.idEcheance = idEcheance;
	}

	public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
