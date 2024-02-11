package com.v1nc3nz0.musimathics.musicfiles.entity;

/*
 * Indica la fine di una battuta.
 * Non viene usata con JMusic.
 * Può essere usata con JFugue
 */
public class Bar implements MusicFileEntity
{

	/*
	 * Ottieni la stringa della battuta
	 * usabile con jfugue
	 */
	@Override
	public String obtain() 
	{
		return "|";
	}
	
	/*
	 * Ottieni la stringa originale
	 * del file musicale
	 */
	@Override
	public String toString()
	{
		return "BAR";
	}

}
