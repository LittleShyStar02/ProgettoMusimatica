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
		Note note;
		Pause pause;
		
		for(MusicFileEntity ent : this)
		{
			if(ent instanceof Note)
			{
				note = (Note) ent;
				compose = compose.concat("m" 
				+ String.valueOf(note.getFrequence()) 
				+ note.getDuration().name().toLowerCase()
				+ " ");
			}
			
			if(ent instanceof Pause)
			{
				pause = (Pause) ent;
				compose = compose.concat(pause.obtain() + " ");
			}
		}
		
		if(compose.endsWith(" ")) compose = compose.substring(0,compose.length()-1);
		
		return compose;
	}
	
}
