package com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.DAO.StatementDAO;
import com.assignment.model.Statement;

@Service
public class StatementServiceImpl implements StatementService{
	
	@Autowired
	private StatementDAO statementDAO;
	@Override
	public List<Statement> findByFromDateAndToDate(long accountId, String fromDate, String toDate) {
		return statementDAO.findByFromDateAndToDate(accountId, fromDate, toDate);
	}

	@Override
	public List<Statement> findByFromAmountAndToAmount(long accountId, String fromAmount, String toAmount) {
		return statementDAO.findByFromAmountAndToAmount(accountId, fromAmount, toAmount);
	}

	@Override
	public List<Statement> find3MonthsStatement(long accountId) {
		return statementDAO.find3MonthsStatement(accountId);
	}

	/*@Override
	public List<Statement> findByDateRangeAndAmountRange(long accountId, String fromDate, String toDate,
			String fromAmount, String toAmount) {
		return statementDAO.findByDateRangeAndAmountRange(accountId, fromDate, toDate, fromAmount, toAmount);
	}*/

}
