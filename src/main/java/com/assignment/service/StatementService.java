package com.assignment.service;

import java.util.List;

import com.assignment.model.Statement;

public interface StatementService {
	public List<Statement> findByFromDateAndToDate(long accountId,String fromDate,String toDate);
	public List<Statement> findByFromAmountAndToAmount(long accountId,String fromAmount,String toAmount);
	//public List<Statement> findByDateRangeAndAmountRange(long accountId,String fromDate,String toDate,String fromAmount,String toAmount);
	public List<Statement> find3MonthsStatement(long accountId);
}
