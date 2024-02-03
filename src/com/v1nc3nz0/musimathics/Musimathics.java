package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.jfugue.player.Player;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.MusicFileEntity;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.storage.musicfiles.entity.Pause;
import com.v1nc3nz0.musimathics.storage.musicfiles.io.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.storage.musicfiles.io.MusicFile;
import com.v1nc3nz0.musimathics.storage.musicfiles.io.MusicFileParser;
import com.v1nc3nz0.musimathics.storage.musicfiles.io.MusicFileReader;
import com.v1nc3nz0.musimathics.storage.musicfiles.io.MusicReader;

public class Musimathics 
{
	
	/*
	 * Da qui parte l'intero programma
	 */
	public static void main(String[] args) throws IOException, InvalidNoteException, LineUnavailableException
	{
		
		MusicFile file = new MusicFile("prova.mf");
		MusicFileReader reader = new MusicFileReader(new MusicReader(file));
		MusicFileParser parser = new MusicFileParser();
		Player player = new Player();
		
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

		Note note;
		Pause pause;
		for(MusicFileEntity ent : entities)
		{
			if(ent instanceof Note)
			{
				note = (Note) ent;
				System.out.println(parser.toString(note));
			}
			
			if(ent instanceof Pause)
			{
				pause = (Pause) ent;
				System.out.println(parser.toString(pause));
			}
		}
		
		
		double frequency = 440.0; // La frequenza che vuoi suonare
        
        player.play("m"+String.valueOf(frequency) + "q");
		
		
	}

}
