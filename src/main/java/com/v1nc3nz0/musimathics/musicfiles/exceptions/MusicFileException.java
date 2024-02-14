package com.v1nc3nz0.musimathics.musicfiles.exceptions;

import com.v1nc3nz0.musimathics.exceptions.MusicException;

/*
 * Eccezione generale del file musicale
 */
@SuppressWarnings("serial")
public class MusicFileException extends MusicException
{
	
	public MusicFileException(String message)
	{
		super(message);
	}
	
}
