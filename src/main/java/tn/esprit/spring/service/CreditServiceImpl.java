package tn.esprit.spring.service;


import java.time.LocalDate;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Compte;
import tn.esprit.spring.entities.Credit;
import tn.esprit.spring.entities.EcheancePaiment;
import tn.esprit.spring.entities.EtatCredit;
import tn.esprit.spring.repos.IClientRepos;
import tn.esprit.spring.repos.ICompteRepos;
import tn.esprit.spring.repos.ICreditRepos;
import tn.esprit.spring.repos.IEcheanceRepos;



@Service
public class CreditServiceImpl implements ICreditService {
	
	@Autowired
	private IClientRepos clientrepos;
	
	@Autowired
	private ICompteRepos compterepos;
	
	@Autowired
	private IEcheanceRepos echancerepos;
	
	@Autowired
	private ICreditRepos creditrepos;
	
	@Autowired
	private TMMManager tmmmanager;
	
	@Autowired
    private MailSender mailSender;
	
	@Override
	public double calculerMensualite(double montant, int d, double taux) {
		    double t = taux / 100;
		    double t1 = montant * t / 12;
		    double t2 = 1 - Math.pow((1 + t / 12), -1 * d);
		    return t1 / t2;
	}
	
	
	public double calculTauxRisque(Credit credit){
		return (credit.getMontantCredit()/credit.getGarantie());
	}
	
	private boolean returnTypeCompte(Long idClient){
		if (creditrepos.returnCompteType(idClient).equals("CompteCourant")){
		return true;}
		return false;
	}
	
	@Override
	public boolean clientPeutCreerCredit(Long idClient) {
		if ((returnTypeCompte(idClient)) &&(creditrepos.countByClientId(idClient)<1)){
			return true;
		}return false;
	}
	
	@Transactional
	@Override
	public void ajouterDemandeCredit(Long idClient, Credit credit){
		Client client= clientrepos.getClient(idClient);
		Compte compte= clientrepos.getbyClient(idClient);
		if(clientPeutCreerCredit(idClient)){	
		Credit cred=new Credit();
		cred.setClient(client);
		cred.setCompte(compte);
		cred.setMontantCredit(credit.getMontantCredit());
		cred.setDateDebut(LocalDate.now());
		cred.setDureeCredit(credit.getDureeCredit());
	    cred.setGarantie(credit.getGarantie());
	    cred.setTauxCredit(tmmmanager.getTMM());
	    cred.setTypecredit(credit.getTypecredit());
	    cred.setEtat(EtatCredit.NOUVEAU);
	    creditrepos.save(cred);
	    client.setCredit(cred);}
	}
	
	@Transactional
	@Override
	public void acceptationCredit(Long idClient){
		Credit credit=clientrepos.getCredit(idClient);
		if (calculTauxRisque(credit)<1){
			credit.setEtat(EtatCredit.ACCEPTE);
			credit.setTauxRisque(calculTauxRisque(credit));
			credit.setDateDebut(LocalDate.now());
			credit.setDateFin(LocalDate.now().plusMonths(credit.getDureeCredit()));
			credit.setMensualite(calculerMensualite(credit.getMontantCredit(), credit.getDureeCredit(), tmmmanager.getTMM()));
			creditrepos.save(credit);
			for (int i=0; i<credit.getDureeCredit();i++){
				EcheancePaiment echeancePaiment = new EcheancePaiment();
				echeancePaiment.setDate(LocalDate.now().plusMonths(i+1));
				echeancePaiment.setMontant(credit.getMensualite());
				echancerepos.save(echeancePaiment);
				echeancePaiment.setCredit(credit);
				}
		}else  
			credit.setEtat(EtatCredit.REFUSE);	
			creditrepos.save(credit);
	}
	
	@Override
	public Credit updateCredit(Long idCredit, LocalDate dateFin){
		 Credit credit= creditrepos.findById(idCredit).get();
		 credit.setDateFin(dateFin);
		return  creditrepos.save(credit);	
		 
	}
	
	@Override
	public void deleteCreditrefus(Long idCredit) {
		Credit credit= creditrepos.findById(idCredit).get();
		if(credit.getEtat().equals(EtatCredit.REFUSE)||(credit.getEcheance()==null)){
			creditrepos.deleteById(idCredit);
		}	
	}

	@Override
	public List<Credit> retrieveAllCreditNouveau() {
		return creditrepos.findByEtat(EtatCredit.NOUVEAU);
	}
	
	@Override
	public List<Credit> retrieveAllCreditValide() {
		return creditrepos.findByEtat(EtatCredit.ACCEPTE);
	}

	@Override
	public List<Credit> retrieveAllCreditRefus() {
		return creditrepos.findByEtat(EtatCredit.REFUSE);
	}
	
	@Transactional
	@Override
	public String afficheClientCredit(Long idClient ) throws JsonProcessingException {
		 Map <Client, Credit > Elif= new HashMap<Client, Credit>();
		 Client client=clientrepos.findById(idClient).get();
		 Credit credit= clientrepos.getCredit(idClient);
		 Elif.put(client, credit);
		 String jsonjackson= new ObjectMapper().writeValueAsString(Elif);
		 return jsonjackson;
	}
	
	
	//@Scheduled(cron = "0 * * * * *")
	@Scheduled(cron = "0 0 0 1 1/1 *")
    public void debiterCredit() {
        List<EcheancePaiment> echeances = creditrepos.findEcheanceByDate(LocalDate.now());
        for (EcheancePaiment echeance : echeances) {
            if (echeance.getMontant()> echeance.getCredit().getCompte().getSoldeCompte()) {
                try {
                	mailSender.sendSimpleMessage(echeance.getCredit().getClient().getEmail(),
                            "Echeance Paiment",
                            "Votre Compte ne contient pas le solde suffisant pour payer votre credit, veuillez regler votre situation!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                compterepos.soustraireDuSoldeCompte(echeance.getCredit().getCompte().getIdCompte(), echeance.getMontant());
            }
        }
    }
	
/*	@Override
	public void createEcheancePaiment(Long idClient){
		Credit credit= clientrepos.getCredit(idClient);
		for (int i=0; i<credit.getDureeCredit();i++){
			EcheancePaiment echeancePaiment = new EcheancePaiment();
			echeancePaiment.setDate(LocalDate.now().plusMonths(i));
			echeancePaiment.setMontant(credit.getMensualite());
			echancerepos.save(echeancePaiment);
			echeancePaiment.setCredit(credit);
			}	
	}*/
	
	@Transactional
	@Override
	public String afficheClientEcheanace(Long idClient ) throws JsonProcessingException {
		 Map <Client, List<EcheancePaiment> > Elif= new HashMap<Client, List<EcheancePaiment>>();
		 Client client=clientrepos.findById(idClient).get();
		 List<EcheancePaiment> echeance= creditrepos.findAllByIdClient(idClient);
		 Elif.put(client, echeance);
		 String jsonjackson= new ObjectMapper().writeValueAsString(Elif);
		 return jsonjackson;
	}

   
	
	
}
