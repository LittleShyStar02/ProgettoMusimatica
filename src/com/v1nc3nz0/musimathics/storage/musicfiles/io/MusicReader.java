package com.v1nc3nz0.musimathics.storage.musicfiles.io;

import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 * Reader musicale
 */
public class MusicReader extends FileReader 
{

	public MusicReader(MusicFile file) throws FileNotFoundException 
	{
		super(file);
	}

}
