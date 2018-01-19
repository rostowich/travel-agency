package com.adsnet.referential.controllers;

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

import com.adsnet.referential.entities.CashRegister;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.ICashRegisterService;
import com.adsnet.referential.validators.CashRegisterRequestValidator;

@Controller
public class CashRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(CashRegisterController.class);

	@Autowired
	private ICashRegisterService cashRegisterService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new CashRegisterRequestValidator());
	}
	
	@RequestMapping(value="cashRegister/all", method=RequestMethod.GET)
	public @ResponseBody List<CashRegister> getAllCities(){
		
		return cashRegisterService.findAll();
	}
	
	@RequestMapping(value="cashRegister", method=RequestMethod.GET)
	public @ResponseBody Page<CashRegister> viewAllCashRegister(@RequestParam Long id, @RequestParam String label, @RequestParam int page){
		
		CashRegister cashRegisterExample=new CashRegister();
		cashRegisterExample.setId(id);
		cashRegisterExample.setLabel(label);
		return cashRegisterService.findAllByExample(cashRegisterExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));
	}
	
	@RequestMapping(value="cashRegister", method=RequestMethod.POST)
	public ResponseEntity<CashRegister>  addCashRegister(@Valid @RequestBody CashRegister cashRegister) throws ItemAlreadyExistException{
		CashRegister cashRegisterSave= cashRegisterService.save(cashRegister);
		return ResponseEntity.ok().body(cashRegisterSave);
	}
	
	@RequestMapping(value="cashRegister/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CashRegister> deleteCashRegister(@PathVariable Long id){
		//We will make sure that the cashRegister exists before deleting it
		Optional<CashRegister> cashRegister=cashRegisterService.findById(id);
		if(!cashRegister.isPresent())
			return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
		
		cashRegisterService.delete(id);
		return new ResponseEntity<CashRegister>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="cashRegister/", method=RequestMethod.PUT)
	public ResponseEntity<CashRegister>  updateCashRegister(@Valid @RequestBody CashRegister cashRegister) throws ItemAlreadyExistException{
		//We will make sure that the cashRegister exists before updating it
		Optional<CashRegister> cashRegisterSearch=cashRegisterService.findById(cashRegister.getId());
		if(!cashRegisterSearch.isPresent())
			return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);		
		
		CashRegister cashRegisterSave= cashRegisterService.save(cashRegister);
		return ResponseEntity.ok().body(cashRegisterSave);
	}
}
