package com.v1nc3nz0.musimathics.entity;

import java.util.HashMap;
import java.util.Map;

import com.v1nc3nz0.musimathics.enums.ScaleIndex;
import com.v1nc3nz0.musimathics.enums.ScaleType;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

import lombok.Getter;

public class Scale 
{
	
	private Map<ScaleIndex,Alteration> alteration;
	private Map<ScaleIndex,NoteName> newindex;
	
	@Getter
	private Note note;
	
	@Getter
	private ScaleType scaleType;
	
	public Scale(Note note, ScaleType type)
	{	
		alteration = new HashMap<>();
		newindex = new HashMap<>();
		this.note = note;
		this.scaleType = type;
		int[] values = type.tones();
		
		try {
			ScaleIndex[] indexes = ScaleIndex.values();
			
			for(int x = 1; x <= values.length;x++)
			{
				int mod = x%values.length;
				note = new Note(note.getSemitone()+values[x-1],type.alt(),note.getDuration());
				alteration.put(indexes[mod], note.getAlteration());
				newindex.put(indexes[mod], note.getNoteName());
			}
		} catch (InvalidNoteException e) {
			//messaggio di errore
		}
	}	
	
	/*
	 * Ottieni l'alterazione della nota nella scala
	 */
	public Alteration getAlteration(ScaleIndex index)
	{
		return alteration.get(index);
	}
	
	/*
	 * 
	 */
	public NoteName getNoteName(ScaleIndex index)
	{
		return newindex.get(index);
	}

}
