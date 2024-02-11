package com.v1nc3nz0.musimathics.enums;

import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

/*
 * Ottiene le note della scala
 * Ogni nota ha un indice nell'array associato
 */
public enum ScaleIndex
{
	C(1,1),D(2,3),E(3,5),F(4,6),G(5,8),A(6,10),B(7,12);
	
	private int arrayIndex;
	private int scaleIndex;
	
	private ScaleIndex(int arrayIndex, int scaleIndex)
	{
		this.arrayIndex = arrayIndex;
		this.scaleIndex = scaleIndex;
	}
	
	public int arrayIndex()
	{
		return arrayIndex;
	}
	
	public int scaleIndex()
	{
		return scaleIndex;
	}
	
	/*
	 * Ottieni il primo indice utile (notazione bemolle)
	 */
	public static ScaleIndex getFirstThen(int index)
	{
		int saveindex = index;
		ScaleIndex toReturn;
		
		while(ScaleIndex.getNearIndex(saveindex) == index && index <= saveindex)
		{
			saveindex++;
		}
		
		if(saveindex > 12) saveindex = 12;
		toReturn = ScaleIndex.getIndex(saveindex);
		
		return toReturn;
	}
	
	/*
	 * Ottieni l'indice passato da parametro
	 */
	public static ScaleIndex getIndex(int value)
	{
		ScaleIndex toReturn = null;;
		for(ScaleIndex index : ScaleIndex.values())
		{
			if(index.scaleIndex() == value)
				toReturn = index;
		}
		return toReturn;
	}
	
	/*
	 * Ottieni l'index
	 */
	public static ScaleIndex getIndex(NoteName name)
	{
		for(ScaleIndex index : ScaleIndex.values())
		{
			if(index.name().equals(name.name().substring(0,name.name().length()-1)))
				return index;
		}
		return null;
	}
	
	/*
	 * Ottieni il primo indice utile (notazione diesis)
	 */
	public static int getNearIndex(int relative_index)
	{
		int toReturn = 0;
		for(ScaleIndex index : ScaleIndex.values())
		{
			if(index.scaleIndex() <= relative_index)
				toReturn = index.scaleIndex();
		}
		if(toReturn == 0) toReturn = 12;
		return toReturn;
	}
	
}
