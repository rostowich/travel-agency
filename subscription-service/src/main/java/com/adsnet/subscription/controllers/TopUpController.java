package com.adsnet.subscription.controllers;

import java.util.Arrays;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.entities.TopUp;
import com.adsnet.subscription.enums.TopUpType;
import com.adsnet.subscription.exceptions.AccountNotFoundException;
import com.adsnet.subscription.exceptions.InsuffisantAmountIntoAccountException;
import com.adsnet.subscription.services.ITopUpService;
import com.adsnet.subscription.validators.TopRequestValidator;

@Controller
public class TopUpController {

	private static final Logger logger = LoggerFactory.getLogger(TopUpController.class);

	@Autowired
	private ITopUpService topUpServiceService;

	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new TopRequestValidator());
	}
		
	@RequestMapping(value="accounting/topupType", method=RequestMethod.GET)
	public @ResponseBody List<TopUpType> getAllAccountType(){
				
		return Arrays.asList(TopUpType.values());
	}
	
	@RequestMapping(value="accounting/topup", method=RequestMethod.GET)
	public @ResponseBody Page<TopUp> viewAllTopUp(@RequestParam String amount, @RequestParam Long accountId,
											      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date creationDateBegin,
												  @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date creationDateEnd,
												  @RequestParam TopUpType topupType, @RequestParam int page){
		 
		TopUp topUpExample=new TopUp();
		topUpExample.setAmount(amount==""?null:amount);
		topUpExample.setCreationDateBegin(creationDateBegin);
		topUpExample.setCreationDateEnd(creationDateEnd);
		topUpExample.setTopUpType(topupType);
		Account account=new Account();
		account.setId(accountId);
		topUpExample.setAccount(account);
		
		System.out.println("La recharge à rechercher "+topUpExample.toString());		
		return topUpServiceService.findAllByExample(topUpExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="accounting/topup", method=RequestMethod.POST)
	public ResponseEntity<TopUp>  addTopUp(@Valid @RequestBody TopUp topup) throws InsuffisantAmountIntoAccountException, AccountNotFoundException{
		System.out.println("la recharhe à insérer est "+topup.toString());
		TopUp toUpSave=topUpServiceService.save(topup);
		return ResponseEntity.ok().body(toUpSave); 		
	}
	
}
