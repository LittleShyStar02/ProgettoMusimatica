package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Enumerazione delle varie alterazioni possibili
 */
public enum Alteration
{
	DOUBLE_FLAT,DOUBLE_NATURAL,DOUBLE_SHARP,FLAT, NATURAL,NONE,SHARP;
	
	/*
	 * Ottieni l'alterazione dalla stringa
	 */
	public static Alteration getAlteration(String alt) throws InvalidNoteException
	{
		switch(alt)
		{
			case "bb":
				return Alteration.DOUBLE_FLAT;
			case "nn":
				return Alteration.DOUBLE_NATURAL;
			case "##":
				return Alteration.DOUBLE_SHARP;
			case "b":
				return Alteration.FLAT;
			case "n":
				return Alteration.NATURAL;
			case "#":
				return Alteration.SHARP;
			default:
				throw new InvalidNoteException(alt + " non è un alterazione valida");
		}
	}
	
	/*
	 * Ottieni il valore dell'alterazione sotto forma di stringa
	 */
	@Override
	public String toString()
	{
		Alteration alt = Alteration.valueOf(name());
		if(alt.equals(Alteration.DOUBLE_FLAT)) return "bb";
		if(alt.equals(Alteration.DOUBLE_NATURAL)) return "BB";
		if(alt.equals(Alteration.DOUBLE_SHARP)) return "##";
		if(alt.equals(Alteration.FLAT)) return "b";
		if(alt.equals(Alteration.NATURAL)) return "B";
		if(alt.equals(Alteration.SHARP)) return "#";
		return null;
	}
}
