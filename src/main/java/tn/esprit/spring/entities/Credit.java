package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Credit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCredit;
	private double montantCredit;
	private int dureeCredit;
	private double tauxCredit;
	@Enumerated(EnumType.STRING)
	private TypeCredit typecredit;
	private double garantie;
	private double mensualite;
	private double tauxRisque;
	@Enumerated(EnumType.STRING)
	private EtatCredit etat;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="credit")
	@JsonIgnore
	private List <EcheancePaiment> echeance;
	@OneToOne
	@JsonIgnore
	private Client client;
	@OneToOne
	@JsonIgnore
	private Compte compte;
	
	public Credit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(long idCredit) {
		this.idCredit = idCredit;
	}

	public double getMontantCredit() {
		return montantCredit;
	}

	public void setMontantCredit(double montantCredit) {
		this.montantCredit = montantCredit;
	}

	public int getDureeCredit() {
		return dureeCredit;
	}

	public void setDureeCredit(int dureeCredit) {
		this.dureeCredit = dureeCredit;
	}

	public double getTauxCredit() {
		return tauxCredit;
	}

	public void setTauxCredit(double tauxCredit) {
		this.tauxCredit = tauxCredit;
	}

	public TypeCredit getTypecredit() {
		return typecredit;
	}

	public void setTypecredit(TypeCredit typecredit) {
		this.typecredit = typecredit;
	}

	public double getGarantie() {
		return garantie;
	}

	public void setGarantie(double garantie) {
		this.garantie = garantie;
	}

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public double getTauxRisque() {
		return tauxRisque;
	}

	public void setTauxRisque(double tauxRisque) {
		this.tauxRisque = tauxRisque;
	}

	public EtatCredit getEtat() {
		return etat;
	}

	public void setEtat(EtatCredit etat) {
		this.etat = etat;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}


	public List<EcheancePaiment> getEcheance() {
		return echeance;
	}

	public void setEcheance(List<EcheancePaiment> echeance) {
		this.echeance = echeance;
	}



	

	
}
