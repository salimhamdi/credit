package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

@Entity
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTransaction;
	private Date dateTransaction;
	private float montant;
	@Enumerated(EnumType.STRING)
	private TypeTransaction transaction;
	@ManyToOne
	private Compte compte;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
	}
	public Date getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	@Override
	public String toString() {
		return "Transaction [idTransaction=" + idTransaction + ", dateTransaction=" + dateTransaction + ", montant="
				+ montant + ", compte=" + compte + "]";
	}
	public TypeTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(TypeTransaction transaction) {
		this.transaction = transaction;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	

}
