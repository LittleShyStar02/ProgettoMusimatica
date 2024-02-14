package com.v1nc3nz0.musimathics.menuselection;

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

import jm.music.data.Part;
import jm.music.data.Score;

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
		String name;
		Score score;
		
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
				if(name == null || name.isBlank())
				{
					//message = setError(Messages.ERROR__INPUT,Messages.DATA__FILE_SETTINGS,Messages.REASON__NULL_VALUE,main);
					continue;
				}
				
				MusicFileSettings settings = new MusicFileSettings(main.getMfSettingsLocation(),name+".yml");
				Scale scale = new Scale(new Note(settings.getNoteScale(),Duration.Q,settings.getNoteAlteration()),
						settings.getScaleType());
				MusicFile music = new MusicFile(main.getMusicFileLocation(),name+".mf");
				
				MusicFileEntityList list = MusicFile.obtainEntities(music, scale);
				
				
			}
			catch (InvalidMusicFileException e) 
			{
				// messaggio
			} 
			catch (InvalidNoteException e) 
			{
				//messaggio
			} catch (IOException e) 
			{
				// messaggio
			}
		}
		while(finish);
		
		
	}

}
