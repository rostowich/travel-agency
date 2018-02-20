package com.maviance.protecting_queen.evaluation;

import com.maviance.protecting_queen.domain.Direction;
import com.maviance.protecting_queen.domain.Evaluation;

public class RightCommandEvaluation implements CommandEvaluation{

	@Override
	public Evaluation evaluateCommand(Evaluation currentEvaluation, String command) {
		// For this command, we have to change only the direction but not the position
		System.out.println("The command is "+command+" Before right "+currentEvaluation.toString());
		Direction newDirection=Direction.valueOf(currentEvaluation.getPosition().getDirection().getRightValue());
		currentEvaluation.getPosition().setDirection(newDirection);
		System.out.println("After right "+currentEvaluation.toString());
		return currentEvaluation;
	}

}
