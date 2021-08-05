package tn.esprit.spring.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.EcheancePaiment;
@Repository
public interface IEcheanceRepos extends JpaRepository<EcheancePaiment, Long> {
	
	
}
