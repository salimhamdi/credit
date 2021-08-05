package tn.esprit.spring.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Compte;
@Repository
public interface ICompteRepos extends JpaRepository<Compte, Long> {

	@Modifying
    @Transactional
    @Query("update Compte c set c.soldeCompte = c.soldeCompte - :montant where c.idCompte = :idCompte")
    public void soustraireDuSoldeCompte(@Param("idCompte") long compte,@Param("montant") double montant);
	

	@Modifying
    @Transactional
	@Query(" DELETE FROM Compte c WHERE c.client.idClient = :idClient")
	public void deleteByIdClient(@Param("idClient")Long idClient);

	
    @Transactional
    @Query("SELECT c FROM Compte c WHERE c.client.idClient= :idClient")
	public List<Compte> getAllbyClient(@Param("idClient")Long idClient);
}
