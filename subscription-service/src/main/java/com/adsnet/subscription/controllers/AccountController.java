package com.adsnet.subscription.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.enums.AccountLimitType;
import com.adsnet.subscription.enums.AccountType;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;
import com.adsnet.subscription.services.IAccountService;
import com.adsnet.subscription.validators.AccountRequestValidator;

@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new AccountRequestValidator());
	}
		
	@RequestMapping(value="referencial/accountType", method=RequestMethod.GET)
	public @ResponseBody List<AccountType> getAllAccountType(){
				
		return Arrays.asList(AccountType.values());
	}
	
	@RequestMapping(value="referencial/accountLimitType", method=RequestMethod.GET)
	public @ResponseBody List<AccountLimitType> getAllAccountLimitType(){
				
		return Arrays.asList(AccountLimitType.values());
	}
	
	@RequestMapping(value="referencial/account/all", method=RequestMethod.GET)
	public @ResponseBody List<Account> getAllAccounts(@RequestParam(required=false) AccountType accountType){
	
		Account accountByExample=new Account();
		accountByExample.setAccountType(accountType);
		
		return accountService.findAllByExample(accountByExample, null).getContent();
	}
	
	
	@RequestMapping(value="referencial/account", method=RequestMethod.GET)
	public @ResponseBody Page<Account> viewAllAccounts(@RequestParam String label, @RequestParam String number,
													   @RequestParam AccountType accountType, @RequestParam AccountLimitType accountLimitType,
													   @RequestParam String amountLimit, @RequestParam int page){
		 
		Account accountExample=new Account();
		accountExample.setLabel(label==""?null:label);
		accountExample.setNumber(number==""?null:number);
		accountExample.setAccountType(accountType);
		accountExample.setAccountLimitType(accountLimitType);
		accountExample.setAmountLimit(amountLimit==""?null:amountLimit);
		
		System.out.println("Le compte à rechercher "+accountExample.toString());
		
		return accountService.findAllByExample(accountExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="referencial/account", method=RequestMethod.POST)
	public ResponseEntity<Account>  addAccount(@Valid @RequestBody Account account) throws ItemAlreadyExistException{
		
		Account accountSave=accountService.save(account);
		return ResponseEntity.ok().body(accountSave); 		
	}
	
	@RequestMapping(value="referencial/account/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Account> deleteAccount(@PathVariable Long id){
		//We will make sure that the account exists before deleting it
		Optional<Account> account=accountService.findAccountById(id);
		if(!account.isPresent())
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		
		accountService.delete(id);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="referencial/account/", method=RequestMethod.PUT)
	public ResponseEntity<Account>  updateAccount(@Valid @RequestBody Account account) throws ItemAlreadyExistException{
		//We will make sure that the account exists before updating it
		System.out.println("le compte à modifier "+account.toString());
		Optional<Account> accountSearch=accountService.findAccountById(account.getId());
		if(!accountSearch.isPresent())
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		
		Account accountSave= accountService.update(account);
		return ResponseEntity.ok().body(accountSave);
	}
	
	
}
