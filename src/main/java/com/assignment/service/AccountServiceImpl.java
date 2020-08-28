package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.DAO.AccountDAO;
import com.assignment.model.Account;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public Account findByAccountNumber(String accountNumber) {
		
		return accountDAO.findByAccountNumber(accountNumber);
	}

}
