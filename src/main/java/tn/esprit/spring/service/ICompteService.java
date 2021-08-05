package tn.esprit.spring.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;


import tn.esprit.spring.entities.Compte;

public interface ICompteService {

	List<Compte> retrieveAllCompte();

	Compte retrieveById(Long idCompte);

	String afficheClientCompte(Long idClient) throws JsonProcessingException;


	
}
