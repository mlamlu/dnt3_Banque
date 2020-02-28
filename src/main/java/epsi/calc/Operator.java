package epsi.calc;

public enum Operator {
	ADDICTION("+"), SUBSTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");
	String description;
	private Operator(String description) {
		this.description = description;
	}
	public static Operator OperatorValidity(String description) {
		for(Operator o : values()) {
			if(o.description.equals(description)) {
				return o;
			}
		}
		return null;
	}
}
