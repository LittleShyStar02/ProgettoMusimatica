package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.util.List;

import org.jfugue.player.Player;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntity;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.entity.Pause;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

public class TestClass 
{
	
	public static void functionTest() throws InvalidNoteException, IOException
	{
		MusicFile file = new MusicFile("prova.mf");
		Player player = new Player();
		
		List<MusicFileEntity> entities = MusicFile.obtainEntities(file);
		
		String sound = "T240 ";
		
		Note note;
		Pause pause;
		for(MusicFileEntity ent : entities)
		{
			if(ent instanceof Note)
			{
				note = (Note) ent;
				sound = sound.concat("m" 
				+ String.valueOf(note.getFrequence()) 
				+ note.getDuration().name().toLowerCase()
				+ " ");
			}
			
			if(ent instanceof Pause)
			{
				pause = (Pause) ent;
				sound = sound.concat(pause.obtain() + " ");
			}
		}
		
		if(sound.endsWith(" ")) sound = sound.substring(0,sound.length()-1);
		
		player.play(sound);
	}

}
