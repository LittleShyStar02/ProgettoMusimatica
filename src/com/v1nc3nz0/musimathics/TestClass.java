package com.v1nc3nz0.musimathics;

import java.io.IOException;
import org.jfugue.player.Player;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

public class TestClass 
{
	
	public static void main(String[] args) throws InvalidNoteException, IOException
	{
		TestClass.functionTest();
	}
	
	public static void functionTest() throws InvalidNoteException, IOException
	{
		MusicFile file = new MusicFile("prova.mf");
		Player player = new Player();
		
		MusicFileEntityList entities = MusicFile.obtainEntities(file);
		
		String sound = "T240 " + entities.compose();
		
		player.play(sound);
	}

}
