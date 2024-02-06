package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.util.List;

import org.jfugue.player.Player;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntity;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.entity.Pause;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

public class TestClass {

	public static void main(String[] args) throws InvalidMusicFileException, InvalidNoteException, IOException 
	{
		
		TestClass.testFile();

	}
	
	public static void testFile() throws InvalidMusicFileException, InvalidNoteException, IOException
	{
		Player player = new Player();
		MusicFile mf = new MusicFile("prova.mf");
		List<MusicFileEntity> ent = MusicFile.obtainEntities(mf);
		
		String sound = "T180 ";
		
		Note note;
		Pause pause;
		for(MusicFileEntity e : ent)
		{
			if(e instanceof Note)
			{
				note = (Note) e;
				sound = sound.concat("m"+String.valueOf(note.getFrequence())+note.getDuration().toString().toLowerCase()+" ");
			}
			if(e instanceof Pause)
			{
				pause = (Pause) e;
				sound = sound.concat(pause.obtain()+" ");
			}
		}
		
		player.play(sound);
	}

}
