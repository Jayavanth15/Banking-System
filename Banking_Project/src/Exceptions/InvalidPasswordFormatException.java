package Exceptions;

public class InvalidPasswordFormatException extends Exception{

	String message;

	public InvalidPasswordFormatException(String message) {
		super(message);
	}
	
}
