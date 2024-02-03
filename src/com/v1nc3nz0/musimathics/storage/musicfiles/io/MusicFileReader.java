package com.v1nc3nz0.musimathics.storage.musicfiles.io;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;

import com.v1nc3nz0.musimathics.storage.musicfiles.entity.MusicFileEntity;

/*
 * Questa classe permette la lettura del file musicale
 */
public class MusicFileReader extends BufferedReader
{

	public MusicFileReader(Reader in) 
	{
		super(in);
	}

	public List<MusicFileEntity> obtainEntities()
	{
		return null;
	}
	

}
