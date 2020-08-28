package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.model.Account;
import com.assignment.model.Statement;
import com.assignment.service.AccountService;
import com.assignment.service.StatementService;

@Controller
@RequestMapping("/statement")
public class StatementController {
	
	public static final Logger logger = LoggerFactory.getLogger(StatementController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StatementService statementService;
	
	@RequestMapping(value="/statement-report", method=RequestMethod.GET)
	public ModelAndView getStatementByReport( @RequestParam(name = "accountNumber") String accountNumber){
		logger.info("*******Inside Controller statement report method ********");
		ModelAndView model = new ModelAndView();
		Account account = accountService.findByAccountNumber(accountNumber);
		String maskedNumber = maskAccountNumber(accountNumber);
		if(account==null||account.getId()==0){
			logger.info("Account id is not found returning model with id not found",maskedNumber);
			model.addObject("message","Account number "+maskedNumber+" Not Found");
			model.setViewName("message_page");
			return model;
		}
		
		List<Statement> statementList =  statementService.find3MonthsStatement(account.getId());
		if(statementList==null||statementList.size()==0){
			logger.info("No records fornd",maskedNumber);
			model.addObject("accountNumber",maskedNumber);
			model.addObject("accountType",account.getAccountType());
			model.addObject("message","No Records");
			model.setViewName("message_page");
			return model;
		}
		model.addObject("accountNumber",maskedNumber);
		model.addObject("accountType",account.getAccountType());
		model.addObject("statementList", statementList);
		model.setViewName("statement_list");
		logger.info("*******End of Controller statement report method ********");
		return model;
	}
	
	@RequestMapping(value="/statement-report-daterange", method=RequestMethod.GET)
	public ModelAndView getStatementByDateRange(
			@RequestParam(name = "accountNumber") String accountNumber,
            @RequestParam(name = "fromDate",required = false) String fromDate ,
                                        @RequestParam(name = "toDate", required = false) String toDate){
		logger.info("*******Inside Controller statement report daterange  method ********");
		ModelAndView model = new ModelAndView();
		Account account = accountService.findByAccountNumber(accountNumber);
		String maskedNumber = maskAccountNumber(accountNumber);
		if(account==null){
			logger.info("Account id is not found returning model with id not found",maskedNumber);
			model.addObject("message","Account number"+maskedNumber+"Not Found");
			model.setViewName("message");
			return model;
		}
		
		if(fromDate!=null&&toDate!=null){
			
			List<Statement> statementList = statementService.findByFromDateAndToDate(account.getId(), fromDate, toDate);
				if(statementList==null||statementList.size()==0){
					logger.info("No records fornd",maskedNumber);
					model.addObject("accountNumber",maskedNumber);
					model.addObject("accountType",account.getAccountType());
					model.addObject("message","No Records");
					model.setViewName("message_page");
					return model;
				}
				model.addObject("accountNumber",maskAccountNumber(accountNumber));
				model.addObject("accountType",account.getAccountType());
				model.addObject("statementList", statementList);
				model.setViewName("statement_list");
				logger.info("*******End of Controller statement report daterange method ********");
				return model;
		}
		return model;
	}
	
	@RequestMapping(value="/statement-report-amountrange", method=RequestMethod.GET)
	public ModelAndView getStatement(
            @RequestParam(name = "accountNumber") String accountNumber,
            @RequestParam(name = "fromAmount", required = false) String fromAmount,
                                        @RequestParam(name = "toAmount",required = false) String toAmount){
		logger.info("*******Inside Controller statement report amount range  method ********");
		
		ModelAndView model = new ModelAndView();
		Account account = accountService.findByAccountNumber(accountNumber);
		String maskedNumber = maskAccountNumber(accountNumber);
		if(account==null){
			logger.info("Account id is not found returning model with id not found",maskedNumber);
			model.addObject("message","Account number"+maskedNumber+"Not Found");
			model.setViewName("message");
			return model;
		}

		if(fromAmount!=null&&toAmount!=null){
			List<Statement> statementList = statementService.findByFromAmountAndToAmount(account.getId(), fromAmount, toAmount);
			if(statementList==null||statementList.size()==0){
				logger.info("No records fornd",maskedNumber);
				model.addObject("accountNumber",maskAccountNumber(accountNumber));
				model.addObject("accountType",account.getAccountType());
				model.addObject("message","No Records");
				model.setViewName("message_page");
				return model;
			}
			model.addObject("accountNumber",maskAccountNumber(accountNumber));
			model.addObject("accountType",account.getAccountType());
			model.addObject("statementList", statementList);
			model.setViewName("statement_list");
			logger.info("*******end of Controller statement report amount range  method ********");
			return model;
		}
		return model;
	}
		String maskAccountNumber(String accountNum){

		    final int ENDLENGTH = 4;    //last digit of card you don't want to mask
		    int maskedLength = accountNum.length() - ENDLENGTH;
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < maskedLength; i++) {
		        sb.append("*");
		    }
		    String maskedAccountNum = sb + accountNum.substring(accountNum.length() - ENDLENGTH, accountNum.length());
		    return maskedAccountNum;
		}
}
