package tn.esprit.spring.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Compte;
import tn.esprit.spring.repos.IClientRepos;
import tn.esprit.spring.repos.ICompteRepos;


@Service
public class CompteServiceImpl implements ICompteService {
	
	@Autowired
	private IClientRepos clientrepos;
	
	@Autowired
	private ICompteRepos compterepos;
	
	

	@Override
	public List<Compte>retrieveAllCompte(){
		List<Compte>comptes=compterepos.findAll();
		return comptes;
	}
	
	@Override
	public Compte retrieveById(Long idCompte){
		return compterepos.findById(idCompte).get();
	}

	public List<Compte> getAllbyClient(Long idClient){
		List<Compte>comptes=compterepos.getAllbyClient(idClient);
		return comptes;
	}	

	
/*	@Override
	public Compte updateCompte(Long idCompte, double soldeCompte){
		Compte compte= compterepos.findById(idCompte).get();
		compte.setSoldeCompte(soldeCompte);
		return compterepos.save(compte);
	}*/
	
	@Transactional
	@Override
	public String afficheClientCompte(Long idClient ) throws JsonProcessingException {
		 Map <Client, List<Compte> > Elif= new HashMap<Client, List<Compte>>();
		 Client client=clientrepos.findById(idClient).get();
		 List<Compte>compte= compterepos.getAllbyClient(idClient);
		 Elif.put(client, compte);
		 String jsonjackson= new ObjectMapper().writeValueAsString(Elif);
		 return jsonjackson;
	}
	
	
}
