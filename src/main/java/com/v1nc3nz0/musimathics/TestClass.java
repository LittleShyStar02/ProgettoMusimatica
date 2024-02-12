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
import com.v1nc3nz0.musimathics.exceptions.MusicException;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.logger.Logger;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFileWriter;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicWriter;

import jm.audio.Instrument;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;

@SuppressWarnings("unused")
public class TestClass 
{
	
	public static void main(String[] args) throws InvalidMusicFileException, IOException, InterruptedException, MusicException
	{	
		trasposeFile();
	}
	
	
	private static void testDate()
	{
		LocalTime date = LocalTime.now();
		System.out.println(date.getHour() + ":" + date.getMinute() + ":" +  date.getSecond());
	}
	
	
	private static void testFileMF() throws InvalidNoteException, IOException
	{
		MusicFile mf = new MusicFile("example.mf");
		MusicFile mf2 = new MusicFile("newexample.mf");
		
		Scale scale = new Scale(new Note(NoteName.C1,Duration.Q,Alteration.NONE),ScaleType.MAJ);
		Scale scale2 = new Scale(new Note(NoteName.G1,Duration.Q,Alteration.NONE),ScaleType.MAJ);
		
		MusicFileEntityList entities = MusicFile.obtainEntities(mf,scale);
		MusicFileEntityList entities2 = MusicFile.obtainEntities(mf2, scale2);
		
		Part part = entities.composeMusicFrequency();
		
		part.setTempo(120); part.setInstrument(Instrument.PIANO);
		  
		Play.midi(part);
		 
		
		part = entities2.composeMusicFrequency();
		part.setTempo(120);
		part.setInstrument(Instrument.PIANO);
		
		Play.midi(part);
	}
	
	
	private static void trasposeFile() throws IOException, MusicException
	{
		MusicFile mf = new MusicFile("example.mf");
		MusicFile mf2 = new MusicFile("newexample.mf");
		if(!mf2.exists()) mf2.createNewFile();
		
		Scale scale = new Scale(new Note(NoteName.C1,Duration.Q,Alteration.NONE),ScaleType.MAJ);
		Scale scale2 = new Scale(new Note(NoteName.A1,Duration.Q,Alteration.NONE),ScaleType.MIN);
		
		MusicFileEntityList entities = MusicFile.obtainEntities(mf,scale);
		MusicFileEntityList traspose = entities.traspose(scale,scale2);
		
		if(mf2.exists()) mf2.createNewFile();
		MusicFileWriter writer = new MusicFileWriter(new MusicWriter(mf2));
		writer.save(traspose,true);
		writer.flush();
		writer.close();
	}

}
