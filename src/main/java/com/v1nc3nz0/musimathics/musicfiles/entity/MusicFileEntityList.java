package com.v1nc3nz0.musimathics.musicfiles.entity;

import java.util.ArrayList;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

/*
 * Lista di entità del file musicale
 */
@SuppressWarnings("serial")
public class MusicFileEntityList extends ArrayList<MusicFileEntity>
{
	
	/*
	 * Trasforma le entità in stringhe da suonare
	 */
	public String compose() throws InvalidNoteException
	{
		String compose = "";
		
		for(MusicFileEntity ent : this)
		{
			compose = compose.concat(ent.obtain() + " ");
		}
		
		compose = compose.substring(0,compose.length()-1);
		
		return compose;
	}
	
}
