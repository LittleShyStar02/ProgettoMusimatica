package com.v1nc3nz0.musimathics.musicfiles.entity;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;
import com.v1nc3nz0.musimathics.musicfiles.enums.ScaleIndex;

/*
 * Rappresenta una scala
 */
public class Scale 
{
	
	private String[] notes;
	private Alteration[] alts;
	
	public Scale()
	{
		// attualmente usiamo solo quella di DO maggiore
		for(ScaleIndex note : ScaleIndex.values())
		{
			notes[note.index()] = note.name();
			alts[note.index()] = Alteration.NONE;
		}
	}
	
	/*
	 * Ottieniamo la nota dell'index avente una
	 * scala cromatica specificata
	 */
	public Note getNote(int index,int chromatic_scale,Duration duration) throws InvalidNoteException
	{
		NoteName name = NoteName.valueOf(notes[index]+String.valueOf(chromatic_scale));
		Alteration alt = alts[index];
		return new Note(name,duration,alt);
	}
	
	

}
