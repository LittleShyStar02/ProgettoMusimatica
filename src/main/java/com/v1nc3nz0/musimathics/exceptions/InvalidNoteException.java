package com.v1nc3nz0.musimathics.exceptions;

/*
 * Eccezione che viene chiamata durante l'utilizz
 * di note non valide
 */
@SuppressWarnings("serial")
public class InvalidNoteException extends MusicException
{

	public InvalidNoteException(String message)
	{
		super(message);
	}
	
}
