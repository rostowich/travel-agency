package com.maviance.protecting_queen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maviance.protecting_queen.domain.Evaluation;
import com.maviance.protecting_queen.services.CommandService;

@RestController
@RequestMapping(value="api/")
public class CommandController {
	
	/**
	 * Dependency injection of the CommandService
	 */
	@Autowired
	private CommandService commandService;
	
	
	@RequestMapping(value="perform", method=RequestMethod.POST)
	public ResponseEntity<Evaluation>  performCommand(@RequestBody String commands) throws Exception {
		
		//We ensure that all the commands are the valid commands
		commandService.isAValidCommand(commands);
		Evaluation currentEvaluation=new Evaluation();
		currentEvaluation=commandService.evaluateAListOfCommand(currentEvaluation, commands);
		return ResponseEntity.ok().body(currentEvaluation); 		
	}
	
}
