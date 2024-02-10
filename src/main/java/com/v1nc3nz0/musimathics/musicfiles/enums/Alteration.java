package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Enumerazione delle varie alterazioni possibili
 */
public enum Alteration
{
	DOUBLE_FLAT("bb"),
	DOUBLE_NATURAL("BB"),
	DOUBLE_SHARP("##"),
	FLAT("b"), 
	NATURAL("B"),
	NONE("NONE"),
	SHARP("#");
	
	private String value;
	
	Alteration(String value)
	{
		this.value = value;
	}
	
	/*
	 * Ottieni l'alterazione dalla stringa
	 */
	public static Alteration getAlteration(String value) throws InvalidNoteException
	{
		
		for(Alteration alt : Alteration.values())
		{
			if(alt.obtain().equals(value)) return alt;
		}
		throw new InvalidNoteException(value + " non è un alterazione valida");
	}
	
	/*
	 * Ottieni il valore dell'alterazione sotto forma di stringa
	 */
	public String obtain()
	{
		return value;
	}
}
