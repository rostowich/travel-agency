package com.maviance.protecting_queen.evaluation;

import com.maviance.protecting_queen.domain.Evaluation;

public class MoveCommandEvaluation implements CommandEvaluation{

	@Override
	public Evaluation evaluateCommand(Evaluation currentEvaluation, String command) {
		// TODO Auto-generated method stub
		//The move evaluation evaluate depending on the value of the direction. In the Direction Enum, we have put those added value
		//for X and for Y position
		System.out.println("The command is "+command+" Before moving "+currentEvaluation.toString());
		currentEvaluation.getPosition().setX(currentEvaluation.getPosition().getX()+currentEvaluation.getPosition().getDirection().getXAddedValue());
		currentEvaluation.getPosition().setY(currentEvaluation.getPosition().getY()+currentEvaluation.getPosition().getDirection().getYAddedValue());
		System.out.println("After moving "+currentEvaluation.toString());
		return currentEvaluation;
	}

}
