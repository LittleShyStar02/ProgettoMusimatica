package com.v1nc3nz0.musimathics.storage.musicfiles.io;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Duration;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Note.Alteration;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Note.Name;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Pause;

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
		
		Name name;
		Duration dur;
		Alteration alt;
		
		name = Name.toName(values[0].toUpperCase());
		dur = Duration.getDuration(values[1].toUpperCase());
		if(values.length == 3) alt = Alteration.getAlteration(values[2]); 
		else alt = Alteration.NONE;
		
		return new Note(name,dur,alt);
	}
	
	/*
	 * Ottiene la pausa da una stringa
	 */
	public Pause toPause(String pause) throws InvalidNoteException
	{
		Duration dur = Duration.getDuration(pause.toUpperCase());
		return new Pause(dur);
	}
	
	/*
	 * Converte la nota in stringa
	 */
	public String toString(Note note)
	{
		String name = note.getName().toString();
		String dur = note.getDuration().toString();
		String alt;
		
		String str = "NOTE " + name + ";" + dur.toLowerCase();
		
		if(note.getAlteration() == Alteration.NONE)
		{
			return str;
		}
		
		alt = note.getAlteration().toString();
		return str + ";" + alt;
		
	}
	
	/*
	 * Converte la pausa in stringa
	 */
	public String toString(Pause pause)
	{
		String dur = pause.getDuration().toString();
		return "PAUSE " + dur.toLowerCase();
	}

}
