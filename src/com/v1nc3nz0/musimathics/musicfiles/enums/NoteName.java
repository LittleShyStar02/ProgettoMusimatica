package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Nome delle note
 */
public enum NoteName
{
	LA0,SI0,
	DO1,RE1,MI1,FA1,SOL1,LA1,SI1,
	DO2,RE2,MI2,FA2,SOL2,LA2,SI2,
	DO3,RE3,MI3,FA3,SOL3,LA3,SI3,
	DO4,RE4,MI4,FA4,SOL4,LA4,SI4,
	DO5,RE5,MI5,FA5,SOL5,LA5,SI5,
	DO6,RE6,MI6,FA6,SOL6,LA6,SI6,
	DO7,RE7,MI7,FA7,SOL7,LA7,SI7,
	DO8;
	
	/*
	 * Controlla se il nome è valido
	 */
	public static boolean isValid(String name)
	{
		try
		{
			NoteName.valueOf(name);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
			
		}
	}
	
	/*
	 * Converte il nome della stringa in costante dell'enum
	 */
	public static NoteName toName(String name) throws InvalidNoteException
	{
		if(!isValid(name)) throw new InvalidNoteException(name + " non riconosiuto come nome nota");
		return NoteName.valueOf(name);
	}
}