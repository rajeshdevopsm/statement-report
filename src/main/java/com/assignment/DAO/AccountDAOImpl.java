package com.assignment.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.model.Account;

@Transactional
@Repository
public class AccountDAOImpl implements AccountDAO{
	public static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Account findByAccountNumber(String accountNumber) {
		logger.info("*******Inside AccountDAOImpl findByAccountNumber method ********");
		String query = "select * from account where account_number=?";
		Account account = new Account();
		try{
			RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
			account = template.queryForObject(query, rowMapper, accountNumber);
			logger.info("*******End of AccountDAOImpl findByAccountNumber method ********");
			return account;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

}
