package com.v1nc3nz0.musimathics.musicfiles.io;

import com.v1nc3nz0.musimathics.entity.Scale;
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
	public Note toNote(String note, Scale scale) throws InvalidNoteException
	{
		String[] values = note.split(";");
		
		NoteName name;
		Duration dur;
		Alteration alt;
		
		name = NoteName.toName(values[0].toUpperCase());
		dur = Duration.getDuration(values[1].toUpperCase());
		alt = scale.getAlteration(name);
		
		if(values.length == 3) 
		{
			Alteration tmp = Alteration.getAlteration(values[2]); 
			if(tmp == Alteration.NATURAL && alt != Alteration.NONE)
			{
				alt = Alteration.NONE;
			}
			else
			{
				alt = tmp;
			}
		}
		
		return new Note(name,dur,alt);
	}
	
	/*
	 * Ottiene la nota da una stringa
	 */
	public NoteList toNoteList(String notelist, Scale scale) throws InvalidNoteException
	{
		String[] notes = notelist.split("\\s");
		NoteList list = new NoteList();
		
		for(String note : notes)
		{
			list.add(toNote(note,scale));
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
