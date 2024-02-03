package com.v1nc3nz0.musimathics.storage.musicfiles.io;

/*
 * Eccezione generata quando qualcosa non va
 * nei file musicali
 */
@SuppressWarnings("serial")
public class InvalidMusicFileException extends MusicFileException
{
	
	public InvalidMusicFileException(String message)
	{
		super(message);
	}
	
}
