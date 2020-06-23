package com.algaworks.domain.exception;

public class NegocioException extends RuntimeException {
	/**
	 * @author Gisele Muniz - 20/06/2020
	 */
	private static final long serialVersionUID = 1L;

	public NegocioException(String message){
		super(message);
	}
}
