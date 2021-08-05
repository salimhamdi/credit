package tn.esprit.spring.service;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import tn.esprit.spring.entities.Credit;

public interface ICreditService {

	double calculerMensualite(double montant, int d, double taux);

	double calculTauxRisque(Credit credit);

	boolean clientPeutCreerCredit(Long idClient);

	void ajouterDemandeCredit(Long idClient, Credit credit);

	//void acceptationCredit(Client client, Credit credit);

	Credit updateCredit(Long idCredit, LocalDate dateFin);

	List<Credit> retrieveAllCreditNouveau();

	void deleteCreditrefus(Long idCredit);

	List<Credit> retrieveAllCreditValide();

	List<Credit> retrieveAllCreditRefus();

	void acceptationCredit(Long idClient);

	String afficheClientCredit(Long idClient) throws JsonProcessingException;

	//void createEcheancePaiment(Long idClient);
	
	String afficheClientEcheanace(Long idClient) throws JsonProcessingException;

	








}
