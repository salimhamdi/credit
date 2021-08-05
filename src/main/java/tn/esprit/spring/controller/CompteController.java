package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.entities.Compte;
import tn.esprit.spring.service.ICompteService;


@RestController
@RequestMapping("/compte")
public class CompteController {

	
	@Autowired
	private ICompteService compteservice;
	
	
	//localhost:8090/SpringMVC/servlet/compte/retrieveAllCompte
	//valide
	@GetMapping("/retrieveAllCompte")
	@ResponseBody
	public List<Compte> retrieveAllCompte(){
		return compteservice.retrieveAllCompte();
	}
	
	//localhost:8090/SpringMVC/servlet/compte/retrieveById
	//valide
	@GetMapping("/retrieveById/{idCompte}")
	@ResponseBody
	public Compte retrieveById(@PathVariable("idCompte")Long idCompte){
		return compteservice.retrieveById(idCompte);
	}
	
	/* //localhost:8090/SpringMVC/servlet/compte/updateCompte/1/500
	@PutMapping("/updateCompte/{idCompte}/{soldeCompte}")
	@ResponseBody
    public void ajouterCompte(@PathVariable("idCompte")Long idCompte, @PathVariable("soldeCompte")double soldeCompte) {
		 compteservice.updateCompte(idCompte, soldeCompte);
	}
	*/
	
	//localhost:8090/SpringMVC/servlet/compte/afficheClientCompte/{idClient}
	//valide
	@GetMapping("/afficheClientCompte/{idClient}")
	@ResponseBody
    public String afficheClientCompte(@PathVariable("idClient")Long idClient) throws JsonProcessingException,JsonMappingException, IOException {
		return compteservice.afficheClientCompte(idClient);
	}
	
  
	
	
	
	
	
}
