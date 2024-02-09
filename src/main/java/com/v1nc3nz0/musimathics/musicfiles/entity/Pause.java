package com.v1nc3nz0.musimathics.musicfiles.entity;

import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * Entità pausa del file musicale
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Pause implements MusicFileEntity
{
	
	private Duration duration;
	
	/*
	 * Ottieni il giusto valore della pausa
	 * basato sulla durata
	 */
	public String obtain()
	{
		if(duration == Duration.W) return "R1";
		if(duration == Duration.H) return "R0.5";
		if(duration == Duration.Q) return "R0.25";
		if(duration == Duration.I) return "R0.125";
		if(duration == Duration.S) return "R0.0625";
		if(duration == Duration.T) return "R0.03125";
		if(duration == Duration.X) return "R0.015625";
		if(duration == Duration.O) return "R0.0078125";
		return null;
	}
	
	@Override
	public String toString()
	{
		return "PAUSE " + duration.toString().toLowerCase();
	}
	
}
