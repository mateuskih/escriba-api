package com.escriba.api.exception;

public class ForbiddenException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
        super(msg);
    }
}
