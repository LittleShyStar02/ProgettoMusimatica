package com.v1nc3nz0.musimathics;

import java.io.IOException;

import org.jfugue.player.Player;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

public class TestClass 
{
	
	public static void main(String[] args) throws InvalidMusicFileException, InvalidNoteException, IOException
	{
		
		TestClass.testProvaMF();
		
	}
	
	private static void testProvaMF() throws InvalidNoteException, IOException
	{
		MusicFile mf = new MusicFile("prova.mf");
		MusicFileEntityList entities = MusicFile.obtainEntities(mf);
		Player player = new Player();
		
		String sound = "T100 ";
		
		sound = sound.concat(entities.compose());
		
		player.play(sound);
	}

}
