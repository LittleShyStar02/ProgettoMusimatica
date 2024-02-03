package com.v1nc3nz0.musimathics.storage.musicfiles.entity;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * Entità nota musicale
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Note implements MusicFileEntity
{
	/*
	 * Enumerazione delle varie alterazioni possibili
	 */
	public enum Alteration
	{
		DOUBLE_FLAT,DOUBLE_NATURAL,DOUBLE_SHARP,FLAT, NATURAL,NONE,SHARP;
		
		/*
		 * Ottieni l'alterazione dalla stringa
		 */
		public static Alteration getAlteration(String alt) throws InvalidNoteException
		{
			switch(alt)
			{
				case "bb":
					return Alteration.DOUBLE_FLAT;
				case "nn":
					return Alteration.DOUBLE_NATURAL;
				case "##":
					return Alteration.DOUBLE_SHARP;
				case "b":
					return Alteration.FLAT;
				case "n":
					return Alteration.NATURAL;
				case "#":
					return Alteration.SHARP;
				default:
					throw new InvalidNoteException(alt + " non è un alterazione valida");
			}
		}
		
		/*
		 * Ottieni il valore dell'alterazione sotto forma di stringa
		 */
		@Override
		public String toString()
		{
			Alteration alt = Alteration.valueOf(name());
			if(alt.equals(Alteration.DOUBLE_FLAT)) return "bb";
			if(alt.equals(Alteration.DOUBLE_NATURAL)) return "BB";
			if(alt.equals(Alteration.DOUBLE_SHARP)) return "##";
			if(alt.equals(Alteration.FLAT)) return "b";
			if(alt.equals(Alteration.NATURAL)) return "B";
			if(alt.equals(Alteration.SHARP)) return "#";
			return null;
		}
	}
	
	/*
	 * Nome delle note
	 */
	public enum Name
	{
		LA0,SI0,
		DO1,RE1,MI1,FA1,SOL1,LA1,SI1,
		DO2,RE2,MI2,FA2,SOL2,LA2,SI2,
		DO3,RE3,MI3,FA3,SOL3,LA3,SI3,
		DO4,RE4,MI4,FA4,SOL4,LA4,SI4,
		DO5,RE5,MI5,FA5,SOL5,LA5,SI5,
		DO6,RE6,MI6,FA6,SOL6,LA6,SI6,
		DO7,RE7,MI7,FA7,SOL7,LA7,SI7,
		DO8;
		
		/*
		 * Controlla se il nome è valido
		 */
		public static boolean isValid(String name)
		{
			try
			{
				Name.valueOf(name);
				return true;
			}
			catch(IllegalArgumentException e)
			{
				return false;
				
			}
		}
		
		/*
		 * Converte il nome della stringa in costante dell'enum
		 */
		public static Name toName(String name) throws InvalidNoteException
		{
			if(!isValid(name)) throw new InvalidNoteException(name + " non riconosiuto come nome nota");
			return Name.valueOf(name);
		}
	}
	
	// frequenza di start per il calcolo delle altre frequenze (A0)
	private final static double START_FREQUENCE = 27.500;
	
	private Name name;
	
	private Duration duration;
	
	private Alteration alteration;
	
	/*
	 * Ottieni la frequenza della nota
	 */
	public double getFrequence()
	{
		int semitone = 0;
		
		//calcolo semitoni
		
		return Note.START_FREQUENCE * Math.pow(1.05946, semitone);
	}
	
}
