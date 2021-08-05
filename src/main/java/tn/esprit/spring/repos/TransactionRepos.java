package tn.esprit.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Transaction;
@Repository
public interface TransactionRepos extends JpaRepository<Transaction, Long>{

}
