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

import com.adsnet.referential.entities.DepartureHour;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.IDepartureHourService;
import com.adsnet.referential.validators.DepartureHourRequestValidator;

@Controller
public class DepartureHourController {

	private static final Logger logger = LoggerFactory.getLogger(DepartureHourController.class);

	@Autowired
	private IDepartureHourService departureHourService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new DepartureHourRequestValidator());
	}
	
	@RequestMapping(value="departure_hour/all", method=RequestMethod.GET)
	public @ResponseBody List<DepartureHour> getAllDepartureHour(){
		
		return departureHourService.findAll();
	}
	
	@RequestMapping(value="departure_hour", method=RequestMethod.GET)
	public @ResponseBody Page<DepartureHour> viewAllDepartureHour(@RequestParam Long id, @RequestParam String label, @RequestParam int page){
		
		DepartureHour departureHourExample=new DepartureHour();
		departureHourExample.setId(id);
		departureHourExample.setLabel(label);
		return departureHourService.findAllByExample(departureHourExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));
	}
	
	@RequestMapping(value="departure_hour", method=RequestMethod.POST)
	public ResponseEntity<DepartureHour>  addDepartureHour(@Valid @RequestBody DepartureHour departureHour) throws ItemAlreadyExistException{
		DepartureHour departureHourSave= departureHourService.save(departureHour);
		return ResponseEntity.ok().body(departureHourSave);
	}
	
	@RequestMapping(value="departure_hour/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<DepartureHour> deleteDepartureHour(@PathVariable Long id){
		//We will make sure that the city exists before deleting it
		Optional<DepartureHour> departureHour=departureHourService.findById(id);
		if(!departureHour.isPresent())
			return new ResponseEntity<DepartureHour>(HttpStatus.NOT_FOUND);
		
		departureHourService.delete(id);
		return new ResponseEntity<DepartureHour>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="departure_hour", method=RequestMethod.PUT)
	public ResponseEntity<DepartureHour>  updateDepartureHour(@Valid @RequestBody DepartureHour departureHour) throws ItemAlreadyExistException{
		//We will make sure that the city exists before updating it
		Optional<DepartureHour> dhSearch=departureHourService.findById(departureHour.getId());
		if(!dhSearch.isPresent())
			return new ResponseEntity<DepartureHour>(HttpStatus.NOT_FOUND);		
		
		DepartureHour departureHourSave= departureHourService.save(departureHour);
		return ResponseEntity.ok().body(departureHourSave);
	}
}
