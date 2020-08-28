package com.assignment.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.assignment.model.Statement;

public class StatementRowMapper implements RowMapper<Statement>{

	@Override
	public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
		Statement statement = new Statement();
		
		statement.setId(rs.getLong("ID"));
		statement.setAccountId(rs.getLong("account_id"));
		statement.setDate(rs.getString("datefield"));
		statement.setAmount(rs.getString("amount"));
		
		return statement;
	}

}
