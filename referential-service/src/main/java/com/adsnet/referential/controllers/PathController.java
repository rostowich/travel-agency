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
import com.adsnet.referential.entities.Path;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.IPathService;
import com.adsnet.referential.validators.PathRequestValidator;


@Controller
public class PathController {

	private static final Logger logger = LoggerFactory.getLogger(PathController.class);

	@Autowired
	private IPathService pathService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new PathRequestValidator());
	}
	
	@RequestMapping(value="path/all", method=RequestMethod.GET)
	public @ResponseBody List<Path> getAllPaths(){
				
		return pathService.findAll();
	}
	
	@RequestMapping(value="path", method=RequestMethod.GET)
	public @ResponseBody Page<Path> viewAllPaths(@RequestParam Long departure, @RequestParam Long destination, @RequestParam int page){
		
		Path pathExample=new Path();
		City departureCity=new City();
		departureCity.setId(departure);
		pathExample.setDeparture(departureCity);
		City destinationCity=new City();
		destinationCity.setId(destination);
		pathExample.setDestination(destinationCity);
		System.out.println("chemin à rechercher "+pathExample.toString());
		
		return pathService.findAllByExample(pathExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="path", method=RequestMethod.POST)
	public ResponseEntity<Path>  addCity(@Valid @RequestBody Path path) throws ItemAlreadyExistException{
		path.setId(path.getDeparture(), path.getDestination());
		Path pathSave=pathService.save(path);
		return ResponseEntity.ok().body(pathSave); 
		
	}
	
	@RequestMapping(value="path/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Path> deleteCity(@PathVariable String id){
		//We will make sure that the path exists before deleting it
		Optional<Path> path=pathService.findPathById(id);
		if(!path.isPresent())
			return new ResponseEntity<Path>(HttpStatus.NOT_FOUND);
		
		pathService.delete(id);
		return new ResponseEntity<Path>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="path/", method=RequestMethod.PUT)
	public ResponseEntity<Path>  updateCity(@Valid @RequestBody Path path) throws ItemAlreadyExistException{
		//We will make sure that the path exists before updating it
		Optional<Path> pathSearch=pathService.findPathById(path.getId());
		if(!pathSearch.isPresent())
			return new ResponseEntity<Path>(HttpStatus.NOT_FOUND);		
		
		Path pathSave= pathService.save(path);
		return ResponseEntity.ok().body(pathSave);
	}
}
