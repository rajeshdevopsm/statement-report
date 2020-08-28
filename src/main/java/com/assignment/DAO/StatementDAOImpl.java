package com.assignment.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.assignment.model.Statement;
import com.assignment.model.StatementRowMapper;

@Repository
public class StatementDAOImpl implements StatementDAO{
	
	public static final Logger logger = LoggerFactory.getLogger(StatementDAOImpl.class);
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Statement> findByFromDateAndToDate(long accountId, String fromDate, String toDate){
		logger.info("*******Inside StatementDAOImpl findByFromDateAndToDate method ********");
		String query = "select * from statement where account_id=? and (datefield>? and datefield<?)";
		try{
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");  
			fromDate = dateFormat.format(date1);
			toDate = dateFormat.format(date2);
		}catch(Exception e){
			logger.error(e.getMessage());
			return null;
		}
		try{
	        RowMapper<Statement> rowMapper = new StatementRowMapper();
	        List<Statement> statementList = template.query(query,rowMapper,accountId,fromDate,toDate);
	        logger.info("*******end of StatementDAOImpl findByFromDateAndToDate method ********",statementList);
	        return statementList;
		}catch(EmptyResultDataAccessException e){
			logger.error(e.getMessage());
			return null;
		}	
	}

	@Override
	public List<Statement> findByFromAmountAndToAmount(long accountId, String fromAmount, String toAmount) {
		logger.info("*******Inside StatementDAOImpl findByFromAmountAndToAmount method ********");
		String query = "select * from statement where account_id=? and (amount>? and amount<?)";
		try{
        RowMapper<Statement> rowMapper = new StatementRowMapper();
	        List<Statement> statementList = template.query(query,rowMapper,accountId,fromAmount,toAmount);
	        logger.info("*******End of StatementDAOImpl findByFromAmountAndToAmount method ********",statementList);
			return statementList;
		}catch(EmptyResultDataAccessException e){
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Statement> find3MonthsStatement(long accountId) {
		logger.info("*******Inside StatementDAOImpl find3MonthsStatement method ********");
		Date date;
		DateFormat dateFormat;
		String fromDate;
		String toDate;
		
		Calendar cal = Calendar.getInstance();
		date = cal.getTime();  
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");  
        toDate = dateFormat.format(date); //today's date
        cal.add(Calendar.MONTH, -3);
        date = cal.getTime();  
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        fromDate = dateFormat.format(date); //3 months back date

        String query = "select * from statement where account_id=? and (datefield>? and datefield<?)";
        try{
	        RowMapper<Statement> rowMapper = new StatementRowMapper();
	        List<Statement> statementList = template.query(query,rowMapper,accountId,fromDate,toDate);
	        logger.info("*******End of  StatementDAOImpl find3MonthsStatement method ********"+statementList);
	        return statementList;
        }catch(EmptyResultDataAccessException e){
        	logger.error(e.getMessage());
        	return null;
        }
	}

}
