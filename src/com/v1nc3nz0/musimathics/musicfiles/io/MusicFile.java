package com.v1nc3nz0.musimathics.musicfiles.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntity;

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
	
	/*
	 * Ottieni tutte le entità del file
	 * in modo da poterle riprodurre
	 */
	public static  List<MusicFileEntity> obtainEntities(MusicFile file) throws InvalidMusicFileException, InvalidNoteException, IOException
	{
		MusicFileReader reader = new MusicFileReader(new MusicReader(file));
		MusicFileParser parser = new MusicFileParser();
		List<MusicFileEntity> entities = new ArrayList<>();
		MusicFileEntity entity;
		String[] values;
		String line = null;
		
		while((line = reader.readLine()) != null)
		{
			values = line.split("\\s");
			switch(values[0])
			{
				case "NOTE":
					entity = parser.toNote(values[1]);
					break;
				case "PAUSE":
					entity = parser.toPause(values[1]);
					break;
				default:
					throw new InvalidMusicFileException("Entità musicale " + values[0] + " non valida");
			}
			entities.add(entity);
		}
		
		reader.close();
		
		return entities;
	}
	
	/*
	 * Ti permette di validare il nome del file
	 */
	private void validate(String fileName) throws InvalidMusicFileException
	{
		if(!fileName.endsWith(".mf")) throw new InvalidMusicFileException("Il file non è musicale. Assicurati di aver dato un file che ha come estensione .mf");
	}
}
