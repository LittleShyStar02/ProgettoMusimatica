package com.v1nc3nz0.musimathics.storage.musicfiles.io;

import java.io.File;

/*
 * File musicale
 */
@SuppressWarnings("serial")
public class MusicFile extends File
{

	public MusicFile(File parent, String child) throws InvalidMusicFileException 
	{
		super(parent, child);
		validate(child);
	}
	
	public MusicFile(String pathname) throws InvalidMusicFileException 
	{
		super(pathname);
		validate(pathname);
	}
	
	public MusicFile(String parent, String child) throws InvalidMusicFileException 
	{
		super(parent, child);
		validate(child);
	}
	
	private void validate(String str) throws InvalidMusicFileException
	{
		if(!str.endsWith("")) throw new InvalidMusicFileException("Il file non è musicale. Assicurati di aver dato un file che ha come estensione .mf");
	}
}
