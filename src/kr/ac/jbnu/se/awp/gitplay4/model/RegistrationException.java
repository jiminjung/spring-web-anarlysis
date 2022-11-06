package kr.ac.jbnu.se.awp.gitplay4.model;

public class RegistrationException extends Exception {
	
	public RegistrationException(String message) {
		super(message);
	}
	
	public RegistrationException(String message, Exception cause) {
		super(message, cause);
	}

}
