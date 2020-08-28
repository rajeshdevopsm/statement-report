package com.assignment.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.assignment.model.Account;

public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("ID"));
		account.setAccountType(rs.getString("account_type"));
		account.setAccountNumber(rs.getString("account_number"));
		return account;
	}

}
