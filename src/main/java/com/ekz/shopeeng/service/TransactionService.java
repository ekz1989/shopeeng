package com.ekz.shopeeng.service;

import java.util.List;

import com.ekz.shopeeng.entity.Transaction;

public interface TransactionService {

	List<Transaction> findTransactionList();

	List<Transaction> findUserTransactionList(int userId);

	Transaction findTransactionById(int transId);

	Transaction findTransactionByToken(String token);

	List<Transaction> findTransactionByStatus(String status);
	
	Transaction save(Transaction trans);
}
