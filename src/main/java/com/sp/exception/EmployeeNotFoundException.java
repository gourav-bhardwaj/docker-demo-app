package com.sp.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897258757670702780L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}
