package com.v1nc3nz0.musimathics.storage.musicfiles.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/*
 * Questa classe permette la scrittura del file musicale
 */
public class MusicFileWriter extends BufferedWriter 
{

	public MusicFileWriter(Writer out) 
	{
		super(out);
	}
	
	public void append(String[] lines) throws IOException
	{
		for(String note : lines)
		{
			append(note);
			newLine();
		}
	}

}
