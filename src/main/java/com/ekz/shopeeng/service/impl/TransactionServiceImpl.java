package com.ekz.shopeeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekz.shopeeng.entity.Transaction;
import com.ekz.shopeeng.repo.TransactionRepository;
import com.ekz.shopeeng.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transRepo;

	@Override
	public List<Transaction> findTransactionList() {
		return (List<Transaction>) transRepo.findAll();
	}

	@Override
	public List<Transaction> findUserTransactionList(int userId) {
		return (List<Transaction>) transRepo.findByUserId(userId);
	}

	@Override
	public Transaction findTransactionById(int transId) {
		return transRepo.findById(transId);
	}

	@Override
	public Transaction findTransactionByToken(String token) {
		return transRepo.findByToken(token);
	}
	
	@Override
	public List<Transaction> findTransactionByStatus(String status) {
		return (List<Transaction>) transRepo.findByStatus(status);
	}

	@Override
	public Transaction save(Transaction trans) {
		return transRepo.save(trans);
	}
}
