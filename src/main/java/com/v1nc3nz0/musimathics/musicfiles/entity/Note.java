package com.v1nc3nz0.musimathics.musicfiles.entity;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteIndex;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

import lombok.Getter;

/*
 * Entità nota musicale
 */
@Getter
public class Note implements MusicFileEntity
{
	
	// frequenza di start per il calcolo delle altre frequenze (A0)
	private final static double START_FREQUENCE = 27.500;
	
	private Alteration alteration;
	
	private Duration duration;
	
	private NoteIndex index;
	
	private NoteName noteName;
	
	private int chromaticScale;
	
	private double frequence;
	
	private int semitone;
	
	public Note(int semitone)
	{
		this.semitone = semitone;
	}
	
	public Note(NoteName noteName,Duration duration,Alteration alteration) throws InvalidNoteException
	{
		this.noteName = noteName;
		this.duration = duration;
		this.alteration = alteration;
		this.index = NoteIndex.getNoteIndex(noteName, alteration);
		this.chromaticScale = Integer.parseInt(String.valueOf(noteName.name().charAt(noteName.name().length()-1)));
		calculate();
	}
	
	/*
	 * Calcola i semitoni e la frequenza della nota
	 */
	private void calculate() throws InvalidNoteException
	{
		semitone = 0;
		
		if(noteName.name().endsWith("0"))
		{
			semitone = Math.abs(12 - index.index() - 2);
		}
		else
		{
			semitone += 2;
			if(noteName.name().endsWith("8")) semitone += 1;
			semitone += (12*(chromaticScale-1) + index.index());
			
		}
		
		if(alteration != Alteration.NONE)
		{
			if(alteration == Alteration.FLAT) semitone--;
			if(alteration == Alteration.DOUBLE_FLAT) semitone -= 2;
			if(alteration == Alteration.SHARP) semitone++;
			if(alteration == Alteration.DOUBLE_SHARP) semitone += 2; 
		}
		
		if(semitone < 0 || semitone > 88) throw new InvalidNoteException("La nota esce fuori dal range di rappresentazione");
		
		/*
		 * Sommiamo 12 per aggiustare il suono
		 * La libreria JFugue considera il DO centrale (DO4)
		 * come DO5, quindi A0 come A1
		 */
		frequence = Note.START_FREQUENCE * Math.pow(1.05946, getSemitone()+12-1);
	}
	
	@Override
	public String obtain() 
	{
		return "m"+frequence+duration.toString();
	}
	

	@Override
	public String toString()
	{
		String str = "NOTE " + noteName.toString() + ";" + duration.toString();
		
		if(getAlteration() == Alteration.NONE)
		{
			return str;
		}
		
		return str + ";" + alteration.toString();
		
	}
	
}
