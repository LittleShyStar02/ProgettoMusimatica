package com.v1nc3nz0.musimathics.musicfiles.io;

import java.io.BufferedReader;
import java.io.Reader;

/*
 * Questa classe permette la lettura del file musicale
 */
public class MusicFileReader extends BufferedReader
{

	public MusicFileReader(Reader in) 
	{
		super(in);
	}
	
}
