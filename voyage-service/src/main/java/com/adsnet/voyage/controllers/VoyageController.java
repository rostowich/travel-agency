package com.adsnet.voyage.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ItemAlreadyExistException;
import com.adsnet.voyage.services.IVoyageService;
import com.adsnet.voyage.validators.VoyageRequestValidator;

@Controller
public class VoyageController {

	private static final Logger logger = LoggerFactory.getLogger(VoyageController.class);

	@Autowired
	private IVoyageService voyageService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new VoyageRequestValidator());
	}
	
	@RequestMapping(value="voyage/all", method=RequestMethod.GET)
	public @ResponseBody List<Voyage> getAllVoyages(){
	
		return voyageService.findAll();
		
	}
		
	@RequestMapping(value="voyage", method=RequestMethod.GET)
	public @ResponseBody Page<Voyage> viewAllVoyages(@RequestParam Boolean archived,
													 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate,
													 @RequestParam Long busId, @RequestParam Long departureHourId,
													 @RequestParam String pathId, @RequestParam int page){
		 
		Voyage voyageExample=new Voyage();
		voyageExample.setArchived(archived);
		voyageExample.setDepartureDate(departureDate);
		voyageExample.setBusId(busId);
		voyageExample.setDepartureHourId(departureHourId);
		voyageExample.setPathId(pathId==""?null:pathId);
		
		return voyageService.findAllByExample(voyageExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="voyage", method=RequestMethod.POST)
	public ResponseEntity<Voyage>  addVoyage(@Valid @RequestBody Voyage voyage) throws ItemAlreadyExistException{
		
		Voyage voyageSave=voyageService.save(voyage);
		return ResponseEntity.ok().body(voyageSave); 		
	}
	
	@RequestMapping(value="voyage/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Voyage> deleteVoyage(@PathVariable String id){
		//We will make sure that the voyage exists before deleting it
		Optional<Voyage> voyage=voyageService.findVoyageById(id);
		if(!voyage.isPresent())
			return new ResponseEntity<Voyage>(HttpStatus.NOT_FOUND);
		
		voyageService.delete(id);
		return new ResponseEntity<Voyage>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="voyage", method=RequestMethod.PUT)
	public ResponseEntity<Voyage>  updateVoyage(@Valid @RequestBody Voyage voyage) throws ItemAlreadyExistException{
		//We will make sure that the account exists before updating it
		Optional<Voyage> voyageSearch=voyageService.findVoyageById(voyage.getId());
		if(!voyageSearch.isPresent())
			return new ResponseEntity<Voyage>(HttpStatus.NOT_FOUND);
		
		Voyage voyageSave= voyageService.update(voyage);
		return ResponseEntity.ok().body(voyageSave);
	}
	
	
}
