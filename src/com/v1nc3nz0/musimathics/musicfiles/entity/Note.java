package com.v1nc3nz0.musimathics.musicfiles.entity;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteIndex;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

import lombok.Getter;
import lombok.Setter;

/*
 * Entità nota musicale
 */
@Getter
@Setter
public class Note implements MusicFileEntity
{
	
	// frequenza di start per il calcolo delle altre frequenze (A0)
	private final static double START_FREQUENCE = 27.500;
	
	private NoteIndex index;
	
	private NoteName noteName;
	
	private Duration duration;
	
	private Alteration alteration;
	
	private int chromaticScale;
	
	public Note(NoteName noteName,Duration duration,Alteration alteration) throws InvalidNoteException
	{
		this.noteName = noteName;
		this.duration = duration;
		this.alteration = alteration;
		this.index = NoteIndex.getNoteIndex(noteName, alteration);
		this.chromaticScale = Integer.parseInt(String.valueOf(noteName.name().toCharArray()[noteName.name().length()-1]));
	}
	
	/*
	 * Ottieni la frequenza della nota
	 * Non considera la scala della nota
	 */
	public double getFrequence() throws InvalidNoteException
	{

		int semitone = 0;
		
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
		
		return Note.START_FREQUENCE * Math.pow(1.05946, semitone-1);
	}
	
}
