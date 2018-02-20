package com.maviance.protecting_queen.evaluation;

import com.maviance.protecting_queen.domain.Evaluation;

public class ReportCommandEvaluation implements CommandEvaluation{

	@Override
	public Evaluation evaluateCommand(Evaluation currentEvaluation, String command) {
		// TODO Auto-generated method stub
		System.out.println("The command is "+command+" Before report "+currentEvaluation.toString());
		return currentEvaluation;
	}

}
