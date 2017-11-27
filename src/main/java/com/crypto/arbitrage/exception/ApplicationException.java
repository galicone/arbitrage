package com.crypto.arbitrage.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = -5534029362081527636L;

	public ApplicationException () {

    }

    public ApplicationException (String message) {
        super (message);
    }

    public ApplicationException (Throwable cause) {
        super (cause);
    }

    public ApplicationException (String message, Throwable cause) {
        super (message, cause);
    }
}
