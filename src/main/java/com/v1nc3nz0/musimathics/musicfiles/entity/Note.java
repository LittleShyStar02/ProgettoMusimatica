package com.v1nc3nz0.musimathics.musicfiles.entity;

import com.v1nc3nz0.musimathics.enums.ScaleIndex;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
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
	
	private ScaleIndex index;
	
	private NoteName noteName;
	
	private int chromaticScale;
	
	private double frequence;
	
	private int semitone;
	
	public Note(int semitone,Alteration altscale, Duration duration) throws InvalidNoteException
	{
		this.semitone = semitone;
		this.duration = duration;
		
		if(semitone == 1) 
		{
			chromaticScale = 0;
			this.noteName = NoteName.A0;
			this.alteration = Alteration.NONE;
		}
		else if(semitone == 2)
		{
			chromaticScale = 0;
			if(altscale == Alteration.SHARP) this.noteName = NoteName.A0;
			if(altscale == Alteration.FLAT) this.noteName = NoteName.B0;
		}
		else if(semitone == 3) 
		{
			chromaticScale = 0;
			this.noteName = NoteName.B0;
			this.alteration = Alteration.NONE;
		}
		else if(semitone == 88) 
		{
			chromaticScale = 8;
			this.noteName = NoteName.C8;
			this.alteration = Alteration.NONE;
		}
		else
		{
			semitone -= 3;
			
			chromaticScale = (semitone/12);
			int scaletone = semitone%12;
			if(scaletone != 0) chromaticScale++;
			
			int index = ScaleIndex.getNearIndex(scaletone);
			int difference = scaletone - index;
			
			this.noteName = NoteName.valueOf(ScaleIndex.getIndex(index).name()+chromaticScale);
			this.alteration = Alteration.NONE;
			
			//System.out.println(semitone+3 + " " + difference + " " + scaletone);
			
			if(difference == 1)
			{
				if(altscale == Alteration.SHARP)
				{
					this.alteration = altscale;
				}
				if(altscale == Alteration.FLAT)
				{
					this.noteName = NoteName.valueOf(ScaleIndex.getFirstThen(index).name()+chromaticScale);
					this.alteration = altscale;
				}
			}
		}
		
		this.index = ScaleIndex.getIndex(noteName);
		frequence = Note.calculateFrequence(semitone);
	}
	
	public Note(NoteName noteName,Duration duration,Alteration alteration) throws InvalidNoteException
	{
		this.noteName = noteName;
		this.duration = duration;
		this.alteration = alteration;
		this.index = ScaleIndex.getIndex(noteName);
		this.chromaticScale = Integer.parseInt(String.valueOf(noteName.name().charAt(noteName.name().length()-1)));
		calculate();
	}
	
	/*
	 * Ottieni la frequenza di una nota
	 */
	public static double calculateFrequence(int semitone)
	{
		return Note.START_FREQUENCE * Math.pow(1.059463,semitone-1);
	}
	
	/*
	 * Calcola i semitoni e la frequenza della nota
	 */
	private void calculate() throws InvalidNoteException
	{
		semitone = 0;
		
		if(getNoteName().name().endsWith("0"))
		{
			semitone = Math.abs(12 - index.scaleIndex() - 2);
		}
		else
		{
			semitone += 3;
			semitone += (12*(chromaticScale-1) + index.scaleIndex());
		}
		
		if(getAlteration() != Alteration.NONE)
		{
			if(getAlteration() == Alteration.FLAT) semitone--;
			if(getAlteration() == Alteration.DOUBLE_FLAT) semitone -= 2;
			if(getAlteration() == Alteration.SHARP) semitone++;
			if(getAlteration() == Alteration.DOUBLE_SHARP) semitone += 2; 
		}
		
		if(semitone < 0 || semitone > 88) throw new InvalidNoteException("La nota esce fuori dal range di rappresentazione");
		
		frequence = Note.calculateFrequence(semitone);
	}
	
	/*
	 * Ottieni la stringa in versione italiana
	 */
	public String itaString()
	{
		String str = "NOTE " + getNoteName().getItalianName() + ";" + getDuration().toString();
		
		if(getAlteration() == Alteration.NONE)
		{
			return str;
		}
		
		return str + ";" + getAlteration().obtain();
	}
	
	/*
	 * Ottiene la stringa della frequenza
	 * per suonarla con JFugue
	 */
	@Override
	public String obtain() 
	{
		return "m"+getFrequence()+getDuration().obtain();
	}
	
	/*
	 * Ottieni la stringa della nota
	 * suonabile con jfugue senza frequenza
	 */
	public String obtainFugue()
	{	
		String name = getNoteName().name();
		String note;
		if(getAlteration() == Alteration.NONE)
		{
			note = name.substring(0,name.length()-1) 
					+ String.valueOf(getChromaticScale()+1) 
					+ getDuration().obtain();
		}
		else
		{
			note = name.substring(0,name.length()-1) 
					+ getAlteration().obtain() 
					+ String.valueOf(getChromaticScale()+1) 
					+ getDuration().obtain();
		}
		return note;
	}
	
	/*
	 * Ottieni la nota come scritta
	 * originariamente nel file musicale
	 */
	@Override
	public String toString()
	{
		String str = "NOTE " + getNoteName().toString() + ";" + getDuration().toString();
		
		if(getAlteration() == Alteration.NONE)
		{
			return str;
		}
		
		return str + ";" + getAlteration().obtain();
		
	}
	
}
