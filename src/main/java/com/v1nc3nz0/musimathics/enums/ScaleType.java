package com.v1nc3nz0.musimathics.enums;

import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;

/*
 * Indicatore per la scala per
 * riconoscere una scala minore dalla maggiore
 */
public enum ScaleType
{
	MAJ(Alteration.SHARP,new int[] {2,2,1,2,2,2,1}),
	MIN(Alteration.FLAT,new int[] {2,1,2,2,1,2,2}),
	MIN_A(Alteration.FLAT,new int[] {2,1,2,2,1,3,1}),
	MIN_M(Alteration.FLAT,new int[] {2,1,2,2,3,2,1});
	
	private Alteration alt_type;
	private int[] tones;
	
	
	ScaleType(Alteration alt_type,int[] tones)
	{
		this.alt_type = alt_type;
		this.tones = tones;
	}
	
	public Alteration alt()
	{
		return alt_type;
	}
	
	public int[] tones()
	{
		return tones;
	}
}
