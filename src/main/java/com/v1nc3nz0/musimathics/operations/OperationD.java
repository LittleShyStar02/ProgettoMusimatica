package com.v1nc3nz0.musimathics.operations;

import java.io.IOException;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.MusicFileSettings;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.entity.Scale;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.graphics.Popup;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

import jm.music.data.Score;
import jm.util.Play;

public class OperationD implements Operation 
{
	
private Musimathics main;
	
	public OperationD(Musimathics main)
	{
		this.main = main;
	}

	@Override
	public void run() 
	{
		boolean finish = true;
		String message = null;
		MusicFileEntityList list;
		MusicFile music;
		String name;
		Score score;
		int voices;
		
		do
		{
			main.getConsole().clear();
			Console.out.println(" ---------------------------------------");
			Console.out.println("|                                       |");
			Console.out.println("|             Operazione D              |");
			Console.out.println("|                                       |");
			Console.out.println(" ---------------------------------------");
			
			try 
			{
				
				if(message != null)
				{
					Console.out.println(message);
					message = null;
				}
				
				name = Popup.showInput(main.getMessages().getMessage(Messages.INPUT__FILE_SETTINGS));
				if(name == null || name.isEmpty())
				{
					message = main.getMessages().getMessage(Messages.ERROR__INPUT);
					message = Placeholder.replace("{data}", main.getMessages().getMessage(Messages.DATA__FILE_SETTINGS), message);
					message = Placeholder.replace("{reason}", main.getMessages().getMessage(Messages.REASON__NULL_VALUE), message);
					continue;
				}
				
				MusicFileSettings settings = new MusicFileSettings(main.getMfSettingsLocation(),name+".yml",main);
				Scale scale = new Scale(new Note(settings.getNoteScale(),Duration.Q,settings.getNoteAlteration()),
						settings.getScaleType());
				
				voices = settings.getVoices();
				score = new Score();
				score.setTempo(settings.getBPM());
				
				for(int x = 0;x < voices;x++)
				{
					music = new MusicFile(main.getMusicFileLocation(),"v"+String.valueOf(x)+"_"+name+".mf");
					if(!music.exists())
					{
						throw new InvalidMusicFileException("\nFile "+"v"+String.valueOf(x)+"_"+name+".mf"+" inesistente");
					}
					list = MusicFile.obtainEntities(music, scale);
					score.add(list.composeMusicFrequency());
				}
				
				// messaggio inizio
				System.setOut(Console.voidout);
				Play.midi(score);
				System.setOut(Console.out);
				//messaggio fine
				finish = false;
				
			}
			catch (InvalidMusicFileException e) 
			{
				Console.out.println(e.getMessage());
			} 
			catch (InvalidNoteException e) 
			{
				Console.out.println(e.getMessage());
			} 
			catch (IOException e) 
			{
				Console.out.println("Errore durante l'utilizzo del file musicale");
			}
			
			Console.out.println();
			main.getConsole().pause();
		}
		while(finish);
		
	}

}
