package com.v1nc3nz0.musimathics.musicfiles.entity;

import java.util.ArrayList;

import com.v1nc3nz0.musimathics.entity.Scale;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.exceptions.MusicException;

import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Rest;

/*
 * Lista di entità del file musicale
 */
@SuppressWarnings("serial")
public class MusicFileEntityList extends ArrayList<MusicFileEntity>
{
	
	/*
	 * Trasforma le entità in stringhe da suonare
	 * usando le frequenze
	 * -> Usiamo JFugue
	 */
	public String composeFugueFrequency() throws InvalidNoteException
	{
		String compose = "";
		
		for(MusicFileEntity ent : this)
		{
			compose = compose.concat(ent.obtain() + " ");
		}
		
		compose = compose.substring(0,compose.length()-1);
		
		return compose;
	}
	
	/*
	 * Trasforma le entità in stringhe da suonare
	 * -> usiamo JFugue
	 */
	public String composeFugueString() throws InvalidNoteException
	{
		String compose = "";
		
		for(MusicFileEntity ent : this)
		{
			if(ent instanceof Note)
			{
				compose = compose.concat(((Note)ent).obtainFugue()+" ");
			}
			else
			{
				compose = compose.concat(ent.obtain() + " ");
			}
		}
		
		compose = compose.substring(0,compose.length()-1);
		
		return compose;
	}
	
	/*
	 * Trasforma le entità in valori da suonare
	 * usando le frequenze
	 * -> Usiamo JMusic
	 */
	public Part composeMusicFrequency()
	{
		
		Part part = new Part();
		
		Note note;
		Pause pause;
		NoteList list;
		
		for(MusicFileEntity ent : this)
		{
			if(ent instanceof NoteList)
			{
				CPhrase multiple = new CPhrase();
				list = (NoteList) ent;
				jm.music.data.Note[] notes = new jm.music.data.Note[list.size()];
				int x = 0;
				
				for(Note value : list)
				{
					notes[x] = new jm.music.data.Note(value.getFrequence(),value.getDuration().obtainMusic());
					x++;
				}
				
				multiple.addChord(notes);
				part.addCPhrase(multiple);
			}
			
			if(ent instanceof Note) 
			{
				note = (Note) ent;
				Phrase single = new Phrase();
				single.add(new jm.music.data.Note(note.getFrequence(),note.getDuration().obtainMusic()));
				part.add(single);
			}
			
			if(ent instanceof Pause)
			{
				pause = (Pause) ent;
				Phrase single = new Phrase();
				single.add(new Rest(pause.getDuration().obtainMusic()));
				part.add(single);
			}

		}
		
		return part;
		
	}

	/*
	 * Porta un brano da una scala a un altra
	 */
	public MusicFileEntityList traspose(Scale oldScale, Scale newScale) throws MusicException 
	{
		if(oldScale.getScaleType() != newScale.getScaleType())
			throw new MusicException("Per trasppore un brano le scale devono avere la stessa tonalità");
		
		MusicFileEntityList ents = new MusicFileEntityList();
		
		try
		{
			Note note;
			Note current;
			for(MusicFileEntity ent : this)
			{
				if(ent instanceof Note)
				{
					current = (Note) ent;
					int difference = newScale.getNote().getIndex().scaleIndex() - oldScale.getNote().getIndex().scaleIndex();;
					note = new Note(current.getSemitone()+difference,newScale.getScaleType().alt(),current.getDuration());
					ents.add(note);
				}
				else ents.add(ent);
			}
		}catch(InvalidNoteException e)
		{
			e.printStackTrace();
		}
		
		return ents;
	}
	
}
