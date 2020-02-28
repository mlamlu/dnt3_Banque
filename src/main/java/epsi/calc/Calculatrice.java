package epsi.calc;

public class Calculatrice {
	public String firstOperand;
	public double firstOperandNumber;
	public String secondOperand;
	public double secondOperandNumber;
	public String operator;
	public double result;
	
	public Calculatrice(String firstOperand, String secondOperand, String operator) {
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
		this.operator = operator;
		if(!validatorOperator(operator)) {
			System.err.println("Operator non conforme");
		}else if(!validatorOperand(firstOperand)){
			System.err.println("First operand is not a valid number");
		}else if(!validatorOperand(secondOperand)) {
			System.err.println("Second operand is not a valid number");
		}else {
			this.firstOperandNumber = StringToDouble(firstOperand);
			this.secondOperandNumber = StringToDouble(secondOperand);
			this.result = resultOperation(firstOperandNumber, secondOperandNumber, operator) ;
		}
	}
	
	public boolean validatorOperator(String operator) {
		if(Operator.OperatorValidity(operator) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validatorOperand(String operand) {
		try {
			Integer.parseInt(operand);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public double StringToDouble(String operand) {
		return (double)Integer.parseInt(operand);
	}
	
	public double resultOperation(double firstOperandNumber, double secondOperandNumber, String operand) {
		switch(operand) {
		case "+":
			return firstOperandNumber+secondOperandNumber;
		case "-":
			return firstOperandNumber-secondOperandNumber;
		case "*":
			return firstOperandNumber*secondOperandNumber;
		case "/":
			return (double)(firstOperandNumber/secondOperandNumber);
		}
		return 0.0;
	}
}
