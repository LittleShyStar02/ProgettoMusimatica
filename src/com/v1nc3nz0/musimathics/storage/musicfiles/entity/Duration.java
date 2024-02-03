package com.v1nc3nz0.musimathics.storage.musicfiles.entity;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Durata della nota
 */
public enum Duration
{
	
	W,H,Q,I,S,T,X,O;
	
	/*
	 * Controlla se la durata è valida
	 */
	public static boolean isValid(String dur)
	{
		try
		{
			Duration.valueOf(dur);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
			
		}
	}
	
	/*
	 * Ottiene la durata da una stringa
	 */
	public static Duration getDuration(String dur) throws InvalidNoteException
	{
		if(!isValid(dur)) throw new InvalidNoteException(dur + " non è una durata valida");
		return Duration.valueOf(dur);
	}

}
