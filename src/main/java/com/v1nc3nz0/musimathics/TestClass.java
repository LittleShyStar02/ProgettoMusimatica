package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import com.v1nc3nz0.musimathics.configuration.MusicFileSettings;
import com.v1nc3nz0.musimathics.entity.Scale;
import com.v1nc3nz0.musimathics.enums.ScaleIndex;
import com.v1nc3nz0.musimathics.enums.ScaleType;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.logger.Logger;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;

import jm.audio.Instrument;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;

@SuppressWarnings("unused")
public class TestClass 
{
	
	public static void main(String[] args) throws InvalidMusicFileException, InvalidNoteException, IOException, InterruptedException
	{
		testFileMF();
	}
	
	
	private static void testDate()
	{
		LocalTime date = LocalTime.now();
		System.out.println(date.getHour() + ":" + date.getMinute() + ":" +  date.getSecond());
	}
	
	
	private static void testFileMF() throws InvalidNoteException, IOException
	{
		MusicFile mf = new MusicFile("example.mf");
		MusicFileSettings mfs = new MusicFileSettings("example.yml");
		Scale scale = new Scale(new Note(mfs.getNoteScale(),Duration.Q,mfs.getNoteAlteration()),mfs.getScaleType());
		MusicFileEntityList entities = MusicFile.obtainEntities(mf,scale);
		
		Part part = entities.composeMusicFrequency();
		part.setTempo(mfs.getBPM());
		part.setInstrument(Instrument.PIANO);
		
		Play.midi(part);
	}

}
