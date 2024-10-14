package Exceptions;

public class NegativeDollarAmountException extends Exception{

	String message;

	public NegativeDollarAmountException(String message) {
		super(message);
	}
	
}
