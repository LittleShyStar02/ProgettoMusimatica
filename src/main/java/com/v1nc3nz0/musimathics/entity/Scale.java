package com.v1nc3nz0.musimathics.entity;

import java.util.HashMap;
import java.util.Map;

import com.v1nc3nz0.musimathics.enums.ScaleIndex;
import com.v1nc3nz0.musimathics.enums.ScaleType;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

public class Scale 
{
	
	private Map<ScaleIndex,Alteration> map;
	
	public Scale(Note note, ScaleType type)
	{	
		map = new HashMap<>();
		int[] values = type.tones();
		
		try {
			map.put(note.getIndex(), note.getAlteration());
			ScaleIndex[] indexes = ScaleIndex.values();
			
			for(int y = 0,x = note.getIndex().arrayIndex(); x <= values.length;x++,y++)
			{
				int mod = x%indexes.length;
				if(map.containsKey(indexes[mod])) continue;
				note = new Note(note.getSemitone()+values[y],type.alt(),note.getDuration());
				map.put(indexes[mod], note.getAlteration());
			}
		} catch (InvalidNoteException e) {
			//messaggio di errore
		}
	}
	
	public Alteration getAlteration(NoteName name)
	{
		return map.get(ScaleIndex.getIndex(name));
	}

}
