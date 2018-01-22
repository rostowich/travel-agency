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

import com.adsnet.referential.entities.City;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.ICityService;
import com.adsnet.referential.validators.CityRequestValidator;

@Controller
public class CityController {

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private ICityService cityService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new CityRequestValidator());
	}
	
	@RequestMapping(value="/city/all", method=RequestMethod.GET)
	public @ResponseBody List<City> getAllCities(){
		
		return cityService.findAll();
	}
	
	@RequestMapping(value="/city", method=RequestMethod.GET)
	public @ResponseBody Page<City> viewAllCities(@RequestParam Long id, @RequestParam String label, @RequestParam int page){
		
		City cityExample=new City();
		cityExample.setId(id);
		cityExample.setLabel(label);
		return cityService.findAllByExample(cityExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));
	}
	
	@RequestMapping(value="city", method=RequestMethod.POST)
	public ResponseEntity<City>  addCity(@Valid @RequestBody City city) throws ItemAlreadyExistException{
		City citySave= cityService.save(city);
		return ResponseEntity.ok().body(citySave);
	}
	
	@RequestMapping(value="city/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<City> deleteCity(@PathVariable Long id){
		//We will make sure that the city exists before deleting it
		Optional<City> city=cityService.findCityById(id);
		if(!city.isPresent())
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		
		cityService.delete(id);
		return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="city", method=RequestMethod.PUT)
	public ResponseEntity<City>  updateCity(@Valid @RequestBody City city) throws ItemAlreadyExistException{
		//We will make sure that the city exists before updating it
		Optional<City> citySearch=cityService.findCityById(city.getId());
		if(!citySearch.isPresent())
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);		
		
		City citySave= cityService.save(city);
		return ResponseEntity.ok().body(citySave);
	}
}
