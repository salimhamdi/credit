package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Compte;


public interface IClientService {


	Client addClient(Client client);

	void updateClientEmail(Long idClient, String email);

	Client retrieveClient(Long idClient);

	void deleteClient(Long idClient);

	List<Client> retrieveAllCLients();

	void createClient(Client client);

	Compte updateCompteCourant(Long idCompte, Long idTransaction, double montant);



	
	
}
