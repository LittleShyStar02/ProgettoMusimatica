package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Durata della nota
 */
public enum Duration
{
	
	W(4.0),H(2.0),Q(1.0),I(0.5),S(0.25),T(0.125),X(0.0625),O(0.03125);
	
	private double jmusic_value;
	
	Duration(double jmusic_value)
	{
		this.jmusic_value = jmusic_value;
	}
	
	/*
	 * Ottiene la durata da una stringa
	 */
	public static Duration getDuration(String dur) throws InvalidNoteException
	{
		if(!isValid(dur)) throw new InvalidNoteException(dur + " non è una durata valida");
		return Duration.valueOf(dur);
	}
	
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
	 * Ottieni la durata
	 */
	public String obtain()
	{
		return name().toLowerCase();
	}
	
	/*
	 * Ottieni la durata secondo lo standardid di jmusic
	 */
	public double obtainMusic()
	{
		return jmusic_value;
	}

}
