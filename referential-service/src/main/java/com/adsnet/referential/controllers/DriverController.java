package com.adsnet.referential.controllers;

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

import com.adsnet.referential.entities.Driver;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.IDriverService;
import com.adsnet.referential.validators.DriverRequestValidator;

@Controller
public class DriverController {

	private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

	@Autowired
	private IDriverService driverService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new DriverRequestValidator());
	}
	
	@RequestMapping(value="driver/all", method=RequestMethod.GET)
	public @ResponseBody List<Driver> getAllDrivers(){
		
		return driverService.findAll();
	}
	
	@RequestMapping(value="driver", method=RequestMethod.GET)
	public @ResponseBody Page<Driver> viewAllDriveres(@RequestParam Long id, @RequestParam String driverLicenceNumber,
						@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date driverLicenceDeliveryBeginDate,
						@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date driverLicenceDeliveryEndDate,
						@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date driverLicenceExpirationBeginDate,
						@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date driverLicenceExpirationEndDate,
						@RequestParam String fullName, @RequestParam String idCardNumber, @RequestParam int page){
		
		Driver driverExample=new Driver();
		driverExample.setId(id);
		driverExample.setFullName(fullName==""?null:fullName);
		driverExample.setDriverLicenceNumber(driverLicenceNumber==""?null:driverLicenceNumber);
		driverExample.setIdCardNumber(idCardNumber==""?null:idCardNumber);
		driverExample.setDriverLicenceDeliveryBeginDate(driverLicenceDeliveryBeginDate);
		driverExample.setDriverLicenceDeliveryEndDate(driverLicenceDeliveryEndDate);
		driverExample.setDriverLicenceExpirationBeginDate(driverLicenceExpirationBeginDate);
		driverExample.setDriverLicenceExpirationEndDate(driverLicenceExpirationEndDate);
				
		System.out.println("le chauffeur à rechercher est "+driverExample.toString());
		return driverService.findAllByExample(driverExample, new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));
	}
	
	@RequestMapping(value="driver", method=RequestMethod.POST)
	public ResponseEntity<Driver>  addDriver(@Valid @RequestBody Driver driver) throws ItemAlreadyExistException{
		System.out.println("le chauffeur sauvegarde est "+driver.toString());
		Driver driverSave= driverService.save(driver);
		return ResponseEntity.ok().body(driverSave);
	}
	
	@RequestMapping(value="driver/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Driver> deleteDriver(@PathVariable Long id){
		//We will make sure that the city exists before deleting it
		Optional<Driver> driver=driverService.findDriverById(id);
		if(!driver.isPresent())
			return new ResponseEntity<Driver>(HttpStatus.NOT_FOUND);
		
		driverService.delete(id);
		return new ResponseEntity<Driver>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="driver/", method=RequestMethod.PUT)
	public ResponseEntity<Driver>  updateDriver(@Valid @RequestBody Driver driver) throws ItemAlreadyExistException{
		//We will make sure that the driver exists before updating it
		Optional<Driver> driverSearch=driverService.findDriverById(driver.getId());
		if(!driverSearch.isPresent())
			return new ResponseEntity<Driver>(HttpStatus.NOT_FOUND);		
		
		Driver driverSave= driverService.update(driver);
		return ResponseEntity.ok().body(driverSave);
	}
}
