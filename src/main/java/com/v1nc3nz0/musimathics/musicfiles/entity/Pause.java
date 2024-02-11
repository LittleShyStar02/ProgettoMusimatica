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
	 * Ottieni il valore della pausa
	 */
	@Override
	public String obtain()
	{
		return "R"+getDuration().obtain();
	}
	
	/*
	 * Ottieni la pausa così
	 * come scritta originariamente
	 * nel file musicale
	 */
	@Override
	public String toString()
	{
		return "PAUSE " + getDuration().name().toLowerCase();
	}
	
}
