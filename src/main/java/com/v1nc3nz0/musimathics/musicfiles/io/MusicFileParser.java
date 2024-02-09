package com.v1nc3nz0.musimathics.musicfiles.io;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.entity.NoteList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Pause;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

/*
 * Parser del file musicale
 */
public class MusicFileParser 
{
	/*
	 * Ottiene la nota da una stringa
	 */
	public Note toNote(String note) throws InvalidNoteException
	{
		String[] values = note.split(";");
		
		NoteName name;
		Duration dur;
		Alteration alt;
		
		name = NoteName.toName(values[0].toUpperCase());
		dur = Duration.getDuration(values[1].toUpperCase());
		if(values.length == 3) alt = Alteration.getAlteration(values[2]); 
		else alt = Alteration.NONE;
		
		return new Note(name,dur,alt);
	}
	
	/*
	 * Ottiene la nota da una stringa
	 */
	public NoteList toNoteList(String notelist) throws InvalidNoteException
	{
		String[] notes = notelist.split("\\s");
		NoteList list = new NoteList();
		
		for(String note : notes)
		{
			list.add(toNote(note));
		}
		
		return list;
	}
	
	/*
	 * Ottiene la pausa da una stringa
	 */
	public Pause toPause(String pause) throws InvalidNoteException
	{
		Duration dur = Duration.getDuration(pause.toUpperCase());
		return new Pause(dur);
	}	

}
