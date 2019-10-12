package com.inomera.telco.commons.lang.fault;

public class UncheckedExecutionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UncheckedExecutionException(Throwable cause) {
		super(cause);
	}
}
