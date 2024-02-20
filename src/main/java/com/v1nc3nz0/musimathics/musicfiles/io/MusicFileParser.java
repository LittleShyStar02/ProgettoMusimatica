package com.v1nc3nz0.musimathics.musicfiles.io;

import com.v1nc3nz0.musimathics.entity.Scale;
import com.v1nc3nz0.musimathics.enums.ScaleIndex;
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
		
		NoteName name,newname;
		Duration dur;
		Alteration altscale,altnote;
		ScaleIndex index;
		
		name = NoteName.toName(values[0].toUpperCase());
		index = ScaleIndex.getIndex(name);
		newname = scale.getNoteName(index);
		if(name != newname) 
		{
			name = NoteName.valueOf(String.valueOf(newname.name().charAt(0))
					+String.valueOf(name.name().charAt(name.name().length()-1)));
		}
		
		dur = Duration.getDuration(String.valueOf(values[1].charAt(0)).toUpperCase());
		if(values[1].toCharArray().length == 2)
		{
			dur.setPointed(true);
		}
		
		altscale = scale.getAlteration(index);
		
		if(values.length == 3) 
		{
			altnote = Alteration.getAlteration(values[2]);
			if(altnote == Alteration.NATURAL && altscale != Alteration.NONE)
			{
				altscale = Alteration.NONE;
			}
			else
			{
				altscale = altnote;
			}
		}
		
		return new Note(name,dur,altscale);
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
		Duration dur = Duration.getDuration(String.valueOf(pause.charAt(0)).toUpperCase());
		if(pause.toCharArray().length == 2)
		{
			dur.setPointed(true);
		}
		return new Pause(dur);
	}

}
