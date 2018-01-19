package com.adsnet.referential.controllers;

import java.util.Arrays;
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

import com.adsnet.referential.entities.Bus;
import com.adsnet.referential.enums.BusType;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.IBusService;
import com.adsnet.referential.validators.BusRequestValidator;

@Controller
public class BusController {

	private static final Logger logger = LoggerFactory.getLogger(BusController.class);

	@Autowired
	private IBusService busService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new BusRequestValidator());
	}
	
	@RequestMapping(value="bus/type", method=RequestMethod.GET)
	public @ResponseBody List<BusType> getAllBusTypes(){
		
		return Arrays.asList(BusType.values());	
	}
	
	@RequestMapping(value="bus/all", method=RequestMethod.GET)
	public @ResponseBody List<Bus> getAllBuses(){
		
		return busService.findAll();
	}
	
	@RequestMapping(value="bus", method=RequestMethod.GET)
	public @ResponseBody Page<Bus> viewAllBuses(@RequestParam Long id, @RequestParam String immatriculation,
						@RequestParam String numberOfPlaces, @RequestParam BusType busType, @RequestParam String mark, @RequestParam int page){
		
		Bus busExample=new Bus();
		busExample.setId(id);
		busExample.setImmatriculation(immatriculation==""?null:immatriculation);
		busExample.setMark(mark==""?null:mark);
		busExample.setType(busType);
		busExample.setNumberOfPlace(numberOfPlaces==""?null:numberOfPlaces);
		System.out.println("le bus à rechercher est "+busExample.toString());
		return busService.findAllByExample(busExample, new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));
	}
	
	@RequestMapping(value="bus", method=RequestMethod.POST)
	public ResponseEntity<Bus>  addBus(@Valid @RequestBody Bus bus) throws ItemAlreadyExistException{
		Bus busSave= busService.save(bus);
		return ResponseEntity.ok().body(busSave);
	}
	
	@RequestMapping(value="bus/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Bus> deleteBus(@PathVariable Long id){
		//We will make sure that the city exists before deleting it
		Optional<Bus> bus=busService.findBusById(id);
		if(!bus.isPresent())
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		
		busService.delete(id);
		return new ResponseEntity<Bus>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="bus/", method=RequestMethod.PUT)
	public ResponseEntity<Bus>  updateBus(@Valid @RequestBody Bus bus) throws ItemAlreadyExistException{
		//We will make sure that the bus exists before updating it
		Optional<Bus> busSearch=busService.findBusById(bus.getId());
		if(!busSearch.isPresent())
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);		
		
		System.out.println("le bus à modifier est "+bus.toString());
		Bus busSave= busService.update(bus);
		return ResponseEntity.ok().body(busSave);
	}
}
