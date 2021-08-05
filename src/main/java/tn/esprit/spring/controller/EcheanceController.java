package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.EcheanceServiceImpl;
import tn.esprit.spring.service.IClientService;
import tn.esprit.spring.service.ICompteService;
import tn.esprit.spring.service.ICreditService;
import tn.esprit.spring.service.IEcheanceService;

@RestController
@RequestMapping("/echeance")
public class EcheanceController {
	@Autowired
	private IClientService clientservice;
	
	@Autowired
	private ICompteService compteservice;
	
	@Autowired
	private ICreditService creditservice;
	
	@Autowired
	private IEcheanceService echanceservice;
	
	
	
	
}
