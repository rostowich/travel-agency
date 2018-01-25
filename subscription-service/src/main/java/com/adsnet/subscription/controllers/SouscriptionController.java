package com.adsnet.subscription.controllers;

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
import com.adsnet.subscription.entities.Souscription;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;
import com.adsnet.subscription.services.ISouscriptionService;
import com.adsnet.subscription.validators.SouscriptionRequestValidator;

@Controller
public class SouscriptionController {

	private static final Logger logger = LoggerFactory.getLogger(SouscriptionController.class);

	@Autowired
	private ISouscriptionService souscriptionService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new SouscriptionRequestValidator());
	}
	
	@RequestMapping(value="accounting/souscription", method=RequestMethod.GET)
	public @ResponseBody Page<Souscription> viewAllSouscriptions(@RequestParam String adress, @RequestParam String cardNumberIdentifier,
													   @RequestParam String completeName, @RequestParam String email,
													   @RequestParam String phoneNumber, @RequestParam Long accountId,
													   @RequestParam int page){
		 
		Souscription souscriptionExample=new Souscription();
		souscriptionExample.setAddress(adress==""?null:adress);
		souscriptionExample.setCardNumberIdentifier(cardNumberIdentifier==""?null:cardNumberIdentifier);
		souscriptionExample.setCompleteName(completeName==""?null:completeName);
		souscriptionExample.setEmail(email==""?null:email);
		souscriptionExample.setPhoneNumber(phoneNumber==""?null:phoneNumber);
		Account account=new Account();
		account.setId(accountId);
		souscriptionExample.setAccount(account);
		
		System.out.println("Le compte à rechercher "+souscriptionExample.toString());
		
		return souscriptionService.findAllByExample(souscriptionExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="accounting/souscription", method=RequestMethod.POST)
	public ResponseEntity<Souscription>  addSouscription(@Valid @RequestBody Souscription souscription) throws ItemAlreadyExistException{
		
		Souscription souscriptionSave=souscriptionService.save(souscription);
		return ResponseEntity.ok().body(souscriptionSave); 		
	}
	
	@RequestMapping(value="accounting/souscription/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Souscription> deleteSouscription(@PathVariable Long id){
		//We will make sure that the souscription exists before deleting it
		Optional<Souscription> souscription=souscriptionService.findSouscriptionById(id);
		if(!souscription.isPresent())
			return new ResponseEntity<Souscription>(HttpStatus.NOT_FOUND);
		
		souscriptionService.delete(id);
		return new ResponseEntity<Souscription>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="accounting/souscription/", method=RequestMethod.PUT)
	public ResponseEntity<Souscription>  updateSouscription(@Valid @RequestBody Souscription souscription) throws ItemAlreadyExistException{
		//We will make sure that the souscription exists before updating it
		System.out.println("le compte à modifier "+souscription.toString());
		Optional<Souscription> souscriptionSearch=souscriptionService.findSouscriptionById(souscription.getId());
		if(!souscriptionSearch.isPresent())
			return new ResponseEntity<Souscription>(HttpStatus.NOT_FOUND);
		
		Souscription souscriptionSave= souscriptionService.update(souscription);
		return ResponseEntity.ok().body(souscriptionSave);
	}
	
	
}
