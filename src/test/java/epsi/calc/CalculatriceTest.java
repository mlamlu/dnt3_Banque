package epsi.calc;

import junit.framework.TestCase;

public class CalculatriceTest extends TestCase {
	
	public void testValidatorOperator() {
		Calculatrice calc = new Calculatrice("3", "2", "+");
		assertTrue(calc.validatorOperator(calc.operator));
		Calculatrice calc2 = new Calculatrice("4", "5", "=");
		assertFalse(calc2.validatorOperator(calc2.operator));
	}
	
	public void testValidatorOperand() {
		Calculatrice calc = new Calculatrice("3","e","+");
		assertTrue(calc.validatorOperand(calc.firstOperand));
		assertFalse(calc.validatorOperand(calc.secondOperand));
	}
	
	public void testResultOperation() {
		Calculatrice calc1 = new Calculatrice("4","6","*");
		assertEquals(calc1.resultOperation(calc1.firstOperandNumber, calc1.secondOperandNumber, calc1.operator), 24.0);
		assertEquals(calc1.resultOperation(16, 43, "+"), 59.0);
	}
}
