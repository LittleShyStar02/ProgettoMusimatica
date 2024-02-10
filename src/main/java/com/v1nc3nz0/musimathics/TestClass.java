package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

@SuppressWarnings("unused")
public class TestClass 
{
	
	public static void main(String[] args) throws InvalidMusicFileException, InvalidNoteException, IOException, InterruptedException
	{
		
		
		testProvaMF();
	}
	
	
	private static void testDate()
	{
		LocalTime date = LocalTime.now();
		System.out.println(date.getHour() + ":" + date.getMinute() + ":" +  date.getSecond());
	}
	
	
	private static void testProvaMF() throws InvalidNoteException, IOException, InterruptedException
	{
		MusicFile mf = new MusicFile("example.mf");
		MusicFileEntityList entities = MusicFile.obtainEntities(mf);
		Player player = new Player();
		
		String sound = "T144 TIME:3/4 I[Piano] ";
		
		sound = sound.concat(entities.compose());
		
		player.play(sound);
	}

}
