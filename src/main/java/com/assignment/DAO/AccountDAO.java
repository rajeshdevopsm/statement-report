package com.assignment.DAO;

import com.assignment.model.Account;

public interface AccountDAO {
	public Account findByAccountNumber(String accountNumber);
}
