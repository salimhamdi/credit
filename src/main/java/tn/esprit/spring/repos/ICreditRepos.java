package tn.esprit.spring.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Credit;
import tn.esprit.spring.entities.EcheancePaiment;
import tn.esprit.spring.entities.EtatCredit;
@Repository
public interface ICreditRepos extends JpaRepository<Credit, Long> {
	
    @Query(value = "select count(credit_id_credit) from client c where c.id_client = :idClient", nativeQuery = true) 
    public int countByClientId(@Param("idClient") Long idClient);
    
    
    @Query(value ="select dtype from compte  where client_id_client = :idClient", nativeQuery = true) 
	public String returnCompteType(@Param("idClient") Long idClient);
    
    
	@Query("select c from Credit c where c.etat = :etat")
	public List<Credit> findByEtat(@Param("etat") EtatCredit etatCredit);
	
	
	@Query("select c from EcheancePaiment c where  c.date = :date")
	public List<EcheancePaiment> findEcheanceByDate(@Param("date") LocalDate date);
	
	//@Query("select e from EcheancePaiment e join Credit c where e.credit.idCredit= :c.idCredit and c.client.idClient = :idClient" )

	@Transactional
	@Query(value="select * from echeance_paiment e join credit c where e.credit_id_credit= c.id_credit and c.client_id_client = :idClient", nativeQuery = true) 
	public List<EcheancePaiment> findAllByIdClient(@Param("idClient") Long idClient);
}

