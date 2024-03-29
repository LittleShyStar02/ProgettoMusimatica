package com.v1nc3nz0.musimathics.configuration.enums;

/*
 * Variabili dei music file settings
 */
public enum MFSettings 
{
	
	MUSIC_BPM,
	MUSIC_SCALE__NOTE,
	MUSIC_SCALE__ALTERATION,
	MUSIC_SCALE__TYPE,
	VOICE_NUMBERS;
	
	@Override
	public String toString()
	{
		String name = name().toLowerCase();
		if(name.contains("__")) name = name.replace("__",".");
		if(name.contains("_")) name = name.replace("_", "-");
		return name;
	}

}
