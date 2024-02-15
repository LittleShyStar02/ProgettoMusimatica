package com.v1nc3nz0.musimathics.musicfiles.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntity;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;

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
	
	/*
	 * Salva le entità musicali sul file associato
	 * all'istanza
	 */
	public void save(MusicFileEntityList entities, boolean universal) throws IOException
	{
		for(MusicFileEntity ent : entities)
		{
			if(ent instanceof Note && universal) append(((Note)ent).itaString());
			else append(ent.toString());
			newLine();
		}
		flush();
	}

}
