package com.v1nc3nz0.musimathics.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Eccezione astratta per creare le altre
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@SuppressWarnings("serial")
public class AbstractException extends Exception
{
	
	public String message;
	
	@Override
	public void printStackTrace()
	{
		System.err.println(message);
	}
	
}