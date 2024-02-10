package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

public enum NoteIndex 
{
	
	C(1), //C_SHARP(2),
	/*D_FLAT(2),*/ D(3), //D_SHARP(4),
	/*E_FLAT(4),*/ E(5),
	F(6), //F_SHARP(7),
	/*G_FLAT(7),*/ G(8), //G_SHARP(9),
	/*A_FLAT(9),*/ A(10), //A_SHARP(11),
	/*B_FLAT(11),*/ B(12);
	
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
