package tn.esprit.spring.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Credit;
import tn.esprit.spring.service.ICreditService;

@RestController
@RequestMapping("/credit")
public class CreditController {
	
		@Autowired
		private ICreditService creditservice;
		
		
	
		//Put:http://localhost:8090/SpringMVC/servlet/credit/calculer/{montant}/{duree}/{taux}
		//valide
		@PutMapping("/calculer/{montant}/{duree}/{taux}")
	    @ResponseBody
	    public double calculMensualite(@PathVariable("montant")double montant, @PathVariable("duree")int duree, @PathVariable("taux")double taux){		
			return creditservice.calculerMensualite(montant, duree, taux);
		}
	
		//Put:http://localhost:8090/SpringMVC/servlet/credit/clientPeutCreerCredit/{idClient}
		//valide
		@PutMapping("/clientPeutCreerCredit/{idClient}")
	    @ResponseBody
		public boolean clientPeurCreerCredit(@PathVariable("idClient")Long idClient){
			return creditservice.clientPeutCreerCredit(idClient);
		}
	
		//Post:http://localhost:8090/SpringMVC/servlet/credit/ajouterDemandeCredit/{idClient}
		//{"montantCredit":4000, "dureeCredit":24 , "typecredit":"Etude", "garantie":5000}
		//valide
		@PostMapping("/ajouterDemandeCredit/{idClient}")
	    @ResponseBody
		public void ajouterDemandeCredit(@PathVariable("idClient")Long idClient, @RequestBody Credit credit){
			 creditservice.ajouterDemandeCredit( idClient, credit);
		}
		
		///Post: http://localhost:8090/SpringMVC/servlet/credit/acceptationCredit/{idClient}		
		//valide
		@PostMapping("/acceptationCredit/{idClient}")
		@ResponseBody
		public void acceptationCredit(@PathVariable("idClient")Long idClient)
		{
			creditservice.acceptationCredit(idClient);
		}
		
		//Put: http://localhost:8090/SpringMVC/servlet/credit/updatecredit/?dateFin=2022-10-10
		//executed once // reason :dateFormat
		@PutMapping("/updatecredit/{idCredit}/{dateFin}")
		@ResponseBody
		public Credit updateCreditPardate(@PathVariable("idCredit")Long idCredit, @PathVariable("dateFin")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate dateFin){
			
			 return creditservice.updateCredit(idCredit, dateFin);
		}
		
		//Delete: http://localhost:8090/SpringMVC/servlet/credit/delete-creditrefus/{idCredit}
		//valide
		@DeleteMapping("/delete-creditrefus/{idCredit}")
		public void deleteClient(@PathVariable("idCredit")Long idCredit){
			creditservice.deleteCreditrefus(idCredit);
		}
		
	/*	//Get:http://localhost:8090/SpringMVC/servlet/credit/trouvercreditvalid
		//valid
		@GetMapping("/trouvercreditvalid")
		@ResponseBody
		public List<Credit>retrieveAllCreditValide(){
			List<Credit>credits=creditservice.retrieveAllCreditValide();
			return credits;
		}*/
		@GetMapping("/trouvercreditvalid")
		public ResponseEntity<Collection<Credit>> retrieveAllCreditValide() {
				Collection<Credit> credits=creditservice.retrieveAllCreditValide();
				return new ResponseEntity<Collection<Credit>>(credits, HttpStatus.FOUND);
		}
		//Get:http://localhost:8090/SpringMVC/servlet/credit/trouvercreditrefus
		//valid
		@GetMapping("/trouvercreditrefus")
		@ResponseBody
		public List<Credit>retrieveAllCreditRefuse(){
			List<Credit>credits=creditservice.retrieveAllCreditRefus();
			return credits;
		}
		
		//Get:http://localhost:8090/SpringMVC/servlet/credit/trouvercreditnouveau
		//valid
		@GetMapping("/trouvercreditnouveau")
		@ResponseBody
		public List<Credit>retrieveAllCreditNouveau(){
			List<Credit>credits=creditservice.retrieveAllCreditNouveau();
			return credits;
		}
		
		//localhost:8090/SpringMVC/servlet/credit/afficheClientCredit/11
		//valide
		@GetMapping("/afficheClientCredit/{idClient}")
		@ResponseBody
	    public String afficheClientCredit(@PathVariable("idClient")Long idClient) throws JsonProcessingException,JsonMappingException, IOException {
			return creditservice.afficheClientCredit(idClient);
		}
		
	/*	///Post: http://localhost:8090/SpringMVC/servlet/credit/createEcheancePaiment/{idClient}		
		//valide
		@PostMapping("/createEcheancePaiment/{idClient}")
		@ResponseBody
		public void createEcheancePaiment(@PathVariable("idClient")Long idClient)
		{
			creditservice.createEcheancePaiment(idClient);
		}
		*/
		//localhost:8090/SpringMVC/servlet/credit/afficheClientEcheanace/1
		//valide
		@GetMapping("/afficheClientEcheanace/{idClient}")
		@ResponseBody
	    public String afficheClientEcheanace(@PathVariable("idClient")Long idClient) throws JsonProcessingException,JsonMappingException, IOException {
			return creditservice.afficheClientEcheanace(idClient);
		}
		
}
