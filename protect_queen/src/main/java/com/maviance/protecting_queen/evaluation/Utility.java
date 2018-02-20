package com.maviance.protecting_queen.evaluation;

import java.util.Arrays;
import java.util.List;

public class Utility {

	/**
	 * This function is used to get the command name (index 0) and all its parameters (others indexes)
	 * @param command
	 * @return
	 */
	public static List<String> getCommandAndParameters(String command){
		//Remove all whitespace after the ',' character
		String commandWithoutUselessWhiteSpace=command.trim().replaceAll(",\\s+", ",");
		String commandReplaced = commandWithoutUselessWhiteSpace.replaceAll(",", " "); 
		List<String> commandAndParameters=Arrays.asList(commandReplaced.trim().split(" "));
		
		return commandAndParameters;
	}
}
