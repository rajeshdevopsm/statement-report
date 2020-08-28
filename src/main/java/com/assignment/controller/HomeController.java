package com.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@GetMapping("/login")
    public String login() {
        return "/login";
    }
	
	@RequestMapping("/")
    public String root() {
        return "login";
    }
	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	@RequestMapping("/get-statement")
    public String getStatement(){
        return "get_statement";
    }
	@RequestMapping("/get-statement-daterange")
    public String getStatementDaterange(){
        return "get_statement_daterange";
    }
	@RequestMapping("/get-statement-amountrange")
    public String getStatementAmountrange(){
        return "get_statement_amountrange";
    }
}
