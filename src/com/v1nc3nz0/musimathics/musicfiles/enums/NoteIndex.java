package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

public enum NoteIndex 
{
	
	DO(1), //DO_SHARP(2),
	/*RE_FLAT(2),*/ RE(3), //RE_SHARP(4),
	/*MI_FLAT(4),*/ MI(5),
	FA(6), //FA_SHARP(7),
	/*SOL_FLAT(7),*/ SOL(8), //SOL_SHARP(9),
	/*LA_FLAT(9),*/ LA(10), //LA_SHARP(11),
	/*SI_FLAT(11),*/ SI(12);
	
	private int scaleIndex;
	
	NoteIndex(int scaleIndex)
	{
		this.scaleIndex = scaleIndex;
	}
	
	public int index()
	{
		return scaleIndex;
	}
	
	/*
	 * Ottieni il note index
	 */
	public static NoteIndex getNoteIndex(NoteName name, Alteration alteration) throws InvalidNoteException 
	{
		String valuename = name.name().substring(0,name.name().length()-1);
		
		// eliminato il controllo sulle alterazioni
		
		if(!isValid(valuename)) throw new InvalidNoteException(valuename + " nota inesistente");
		
		return NoteIndex.valueOf(valuename);
	}
	
	/*
	 * Controlla se la nota è valida
	 */
	public static boolean isValid(String noteindex)
	{
		try
		{
			NoteIndex.valueOf(noteindex);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
			
		}
	}

}
