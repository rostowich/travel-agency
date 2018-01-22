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

import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.entities.Path;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.IClassService;
import com.adsnet.referential.validators.ClassRequestValidator;


@Controller
public class ClasseController {

	private static final Logger logger = LoggerFactory.getLogger(ClasseController.class);

	@Autowired
	private IClassService classService;
	
	@Autowired
	private Environment environment;
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new ClassRequestValidator());
	}
	
	@RequestMapping(value="class/all", method=RequestMethod.GET)
	public @ResponseBody List<Classe> getAllClasses(){
		
		return classService.findAll();
	}
	
	@RequestMapping(value="/class", method=RequestMethod.GET)
	public @ResponseBody Page<Classe> viewAllClasses(@RequestParam String label, @RequestParam String pathId, @RequestParam int page){
		
		Classe classExample=new Classe();
		classExample.setLabel(label);
		Path path=new Path();
		pathId=(pathId==""?null:pathId);
		path.setId(pathId);
		classExample.setPath(path);
		
		return classService.findAllByExample(classExample,new PageRequest(page, Integer.parseInt(environment.getProperty("pages.number"))));		
	}
	
	@RequestMapping(value="class", method=RequestMethod.POST)
	public ResponseEntity<Classe>  addClass(@Valid @RequestBody Classe classe) throws ItemAlreadyExistException{
		classe.setId(classe.getPath(), classe.getLabel());
		Classe classeSave=classService.save(classe);
		return ResponseEntity.ok().body(classeSave); 
		
	}
	
	@RequestMapping(value="class/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Classe> deleteClass(@PathVariable String id){
		//We will make sure that the path exists before deleting it
		Optional<Classe> classe=classService.findById(id);
		if(!classe.isPresent())
			return new ResponseEntity<Classe>(HttpStatus.NOT_FOUND);
		
		classService.delete(id);
		return new ResponseEntity<Classe>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="class", method=RequestMethod.PUT)
	public ResponseEntity<Classe>  updateClass(@RequestBody Classe classe) throws ItemAlreadyExistException{
		//We will make sure that the path exists before updating it
		Optional<Classe> classeId=classService.findById(classe.getId());
		if(!classeId.isPresent())
			return new ResponseEntity<Classe>(HttpStatus.NOT_FOUND);		
		
		System.out.println("la classe à modifier est "+classe.toString());
		Classe classSave= classService.save(classe);
		System.out.println("la classe modifiée est "+classSave.toString());
		return ResponseEntity.ok().body(classSave);
	}
}
