package tn.esprit.spring.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Compte;
import tn.esprit.spring.entities.CompteCourant;
import tn.esprit.spring.entities.EtatCompte;
import tn.esprit.spring.entities.Transaction;
import tn.esprit.spring.entities.TypeTransaction;
import tn.esprit.spring.repos.IClientRepos;
import tn.esprit.spring.repos.ICompteRepos;
import tn.esprit.spring.repos.TransactionRepos;
@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientRepos clientrepos;
	
	@Autowired
	private ICompteRepos compterepos;
	
	@Autowired
	private TransactionRepos transactionrepos;
	
		
	 
	    public boolean existsByCin(String cin) {
	        return clientrepos.existsByCin(cin);
	    }

		@Override
		public Client addClient(Client client) {
			return clientrepos.save(client);
		}

		@Override
		public void updateClientEmail(Long idClient, String email) {
			Client client= clientrepos.findById(idClient).get();	
			client.setEmail(email);
			clientrepos.save(client);
		}

		@Override
		public Client retrieveClient(Long idClient) {
			return clientrepos.findById(idClient).orElse(null);
		}

		@Override
		public List<Client> retrieveAllCLients() {
			List<Client>clients=clientrepos.findAll();
			return clients;
		}

		@Override
		public void deleteClient(Long idClient) {
			Client client=clientrepos.findById(idClient).get();
			if(client.getCredit().getMensualite()<=0){
				compterepos.deleteByIdClient(idClient);
				clientrepos.delete(client);
			}	
		}
		
		@Transactional
	    @Override
		public void createClient(Client client) {
			if (clientrepos.existsByCin(client.getCin())){
	            return;}
	    	else{
			clientrepos.save(client);
	        CompteCourant compte = new CompteCourant();
	        compte.setClient(client);
	        compte.setDateCreationCompte(new Date());
	        compte.setEtatCompte(EtatCompte.Debiteur);
	        compte.setSoldeCompte(50);
	        compte.setDecouvert(compte.getDecouvert());
	        compterepos.save(compte);}
	    }
		
		/*private boolean ajoutSoldeCompte(Long idTransaction){
			Transaction transaction= transactionrepos.findById(idTransaction).get();
	        transaction.setTransaction(TypeTransaction.depot);
			return true;		
		}*/
		
		@Transactional
		@Override
		public Compte updateCompteCourant(Long idCompte, Long idTransaction,  double montant) {
			Compte compte= compterepos.findById(idCompte).get();
			if(compte instanceof CompteCourant){
			Transaction transaction=transactionrepos.findById(idTransaction).get();
			if (transaction.getTransaction().equals(TypeTransaction.depot)){
				compte.setSoldeCompte(compte.getSoldeCompte()+montant);
				((CompteCourant) compte).setDecouvert(((CompteCourant) compte).getDecouvert());
				if(compte.getSoldeCompte()<0){
					compte.setEtatCompte(EtatCompte.Crediteur);
				}else compte.setEtatCompte(EtatCompte.Debiteur);
			}else{
				compte.setSoldeCompte(compte.getSoldeCompte()-montant);
				((CompteCourant) compte).setDecouvert(((CompteCourant) compte).getDecouvert());
			if(compte.getSoldeCompte()<0){
				compte.setEtatCompte(EtatCompte.Crediteur);
			}else compte.setEtatCompte(EtatCompte.Debiteur);
			}}			
			return compterepos.save(compte);
		}


		
		
		
		
		
		
}
