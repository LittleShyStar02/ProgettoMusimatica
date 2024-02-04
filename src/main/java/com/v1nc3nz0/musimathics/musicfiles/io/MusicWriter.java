package com.v1nc3nz0.musimathics.musicfiles.io;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Writer musicale
 */
public class MusicWriter extends FileWriter 
{

	public MusicWriter(MusicFile file) throws IOException 
	{
		super(file);
	}
	
	public MusicWriter(MusicFile file,boolean append) throws IOException 
	{
		super(file,append);
	}



}
