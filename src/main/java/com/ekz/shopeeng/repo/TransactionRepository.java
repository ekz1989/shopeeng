package com.ekz.shopeeng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekz.shopeeng.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	@Query(value = "SELECT n FROM Transaction n WHERE n.userId = ?1")
	List<Transaction> findByUserId(int userId);
	
	@Query(value = "SELECT n FROM Transaction n WHERE n.transactionId = ?1")
	Transaction findById(int transactionId);

	@Query(value = "SELECT n FROM Transaction n WHERE n.token = ?1")
	Transaction findByToken(String token);

	@Query(value = "SELECT n FROM Transaction n WHERE n.status = ?1")
	List<Transaction> findByStatus(String status);
}
