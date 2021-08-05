package tn.esprit.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Compte;
import tn.esprit.spring.entities.Credit;

@Repository
public interface IClientRepos extends JpaRepository<Client, Long>{
	
	boolean existsByCin(String cin);
	
	 @Transactional
	 @Query(value ="select * from compte  where client_id_client = :idClient", nativeQuery = true) 
	 public Compte returnCompte(@Param("idClient") Long idClient);
	
	 @Transactional
	 @Query("SELECT c FROM Compte c WHERE c.client.idClient= :idClient")
	 public Compte getbyClient(@Param("idClient")Long idClient);
	 
	 @Transactional
	 @Query("SELECT c FROM Client c WHERE c.idClient= :idClient")
	 public Client getClient(@Param("idClient")Long idClient);
	 
	 @Transactional
	 @Query("SELECT c FROM Credit c WHERE c.client.idClient= :idClient")
	 public Credit getCredit(@Param("idClient")Long idClient);
}
