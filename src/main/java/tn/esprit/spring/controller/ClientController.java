package tn.esprit.spring.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.entities.Client;
import tn.esprit.spring.service.IClientService;


@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private IClientService clientservice;
		
	
	
		//localhost:8090/SpringMVC/servlet/client/add-client
		//{"nomPrenomClient": "hamdi Sami","cin": "07210203","dateDeNaissance": "1990-14-14",	"email": "fdgfdg@dfgfdg.com", "type": "Physique"}
		//valide
		@PostMapping("/add-client")
		@ResponseBody
		public Client addClient(@RequestBody Client client){
			clientservice.addClient(client);
			return client;
		}
		
		//localhost:8090/SpringMVC/servlet/client/trouve-client/{idClient}
		//valide
		@GetMapping("/trouve-client/{idClient}")
		@ResponseBody
		public ResponseEntity<?> trouveClient(@PathVariable("idClient")Long idClient) {
	        return new ResponseEntity<>(clientservice.retrieveClient(idClient), HttpStatus.FOUND);
		}
		
	    //localhost:8090/SpringMVC/servlet/client/retrieve-all-clients
		//valide
		@GetMapping("/retrieve-all-clients")
		public ResponseEntity<Collection<Client>> getAllUsers() {
				Collection<Client> clients = clientservice.retrieveAllCLients();
				return new ResponseEntity<Collection<Client>>(clients, HttpStatus.FOUND);
		}
		
		//localhost:8090/SpringMVC/servlet/client/updateClientEmail/1/hamdisleem@yahoo.fr
		//valide
		@PutMapping("/updateClientEmail/{idClient}/{email}")
		@ResponseBody
		public void modifyClient(@PathVariable("idClient")Long idClient, @PathVariable("email")String email){
			clientservice.updateClientEmail( idClient, email);	
		}
		
		//localhost:8090/SpringMVC/servlet/client/delete-ClientEtCompte/{idClient}
		//valide
		@DeleteMapping("/delete-ClientEtCompte/{idClient}")
		public void deleteClientEtCompte(@PathVariable("idClient")Long idClient){
			clientservice.deleteClient(idClient);
		}
		//localhost:8090/SpringMVC/servlet/client/affecterClientCompte
		//{"nomPrenomClient": "hamdi Sami","cin": "07145896","dateDeNaissance": "1990-14-14",	"email": "fdgfdg@dfgfdg.com", "type": "Physique"}
		//valide
		@PostMapping("/affecterClientCompte")
		@ResponseBody
		public void affecterClientCompte(@RequestBody Client client){
			  clientservice.createClient(client);		
		}
	
		//localhost:8090/SpringMVC/servlet/client/updateCompteCourant/{idCompte}/{idTransaction}/{montant}
		//valide
		@PutMapping("/updateCompteCourant/{idCompte}/{idTransaction}/{montant}")
		@ResponseBody
		public void updateCompte(@PathVariable("idCompte")Long idCompte, @PathVariable("idTransaction")Long idTransaction, @PathVariable("montant")double montant){
			clientservice.updateCompteCourant(idCompte, idTransaction, montant);
		}
		
		
		

}
