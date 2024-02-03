package com.v1nc3nz0.musimathics.musicfiles.enums;

/*
 * Ottiene le note della scala
 * Ogni nota ha un indice nell'array associato
 */
public enum ScaleIndex
{
	DO(0),RE(1),MI(2),FA(3),SOL(4),LA(5),SI(6);
	
	private int arrayIndex;
	
	private ScaleIndex(int arrayIndex)
	{
		this.arrayIndex = arrayIndex;
	}
	
	public int index()
	{
		return arrayIndex;
	}
}
