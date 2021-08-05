package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repos.IClientRepos;
import tn.esprit.spring.repos.ICompteRepos;
import tn.esprit.spring.repos.IEcheanceRepos;
@Service
public class EcheanceServiceImpl implements IEcheanceService {

	@Autowired
	IClientRepos clientrepos;
	
	@Autowired
	ICompteRepos compterepos;
	
	@Autowired
	IEcheanceRepos echancerepos;
	
	@Autowired
	ICreditService creditrepos;
	
	
	
}
