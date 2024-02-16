package com.v1nc3nz0.musimathics.operations;

import java.io.IOException;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.MusicFileSettings;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.entity.Scale;
import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;
import com.v1nc3nz0.musimathics.exceptions.MusicException;
import com.v1nc3nz0.musimathics.graphics.Popup;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.musicfiles.entity.MusicFileEntityList;
import com.v1nc3nz0.musimathics.musicfiles.entity.Note;
import com.v1nc3nz0.musimathics.musicfiles.enums.Duration;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFile;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicFileWriter;
import com.v1nc3nz0.musimathics.musicfiles.io.MusicWriter;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

public class OperationE implements Operation 
{
	
private Musimathics main;
	
	public OperationE(Musimathics main)
	{
		this.main = main;
	}

	@Override
	public void run() 
	{
		boolean finish = true;
		String message = null;
		
		MusicFileEntityList list,list2;
		MusicFileWriter writer;
		MusicFile music,music2;
		String name,name2;
		int voices;
		
		do
		{
			main.getConsole().clear();
			Console.out.println(" ---------------------------------------");
			Console.out.println("|                                       |");
			Console.out.println("|             Operazione E              |");
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
				
				name2 = Popup.showInput(main.getMessages().getMessage(Messages.INPUT__NEW_FILE_SETTINGS));
				if(name2 == null || name.isEmpty())
				{
					message = main.getMessages().getMessage(Messages.ERROR__INPUT);
					message = Placeholder.replace("{data}", main.getMessages().getMessage(Messages.DATA__FILE_SETTINGS), message);
					message = Placeholder.replace("{reason}", main.getMessages().getMessage(Messages.REASON__NULL_VALUE), message);
					continue;
				}
				
				MusicFileSettings oldsettings = new MusicFileSettings(main.getMfSettingsLocation(),name+".yml",main);
				MusicFileSettings newsettings = new MusicFileSettings(main.getMfSettingsLocation(),name2+".yml",main);
				
				Scale oldscale = new Scale(
						new Note(
								oldsettings.getNoteScale(),
								Duration.Q,
								oldsettings.getNoteAlteration()
						),
						oldsettings.getScaleType()
				);
				
				Scale newscale = new Scale(
						new Note(
								newsettings.getNoteScale(),
								Duration.Q,
								newsettings.getNoteAlteration()
						),
						newsettings.getScaleType()
				);
				
				voices = oldsettings.getVoices();
				
				for(int x = 0;x < voices;x++)
				{
					music = new MusicFile(main.getMusicFileLocation(),"v"+String.valueOf(x)+"_"+name+".mf");
					if(!music.exists())
					{
						throw new InvalidMusicFileException("\nFile "+"v"+String.valueOf(x)+"_"+name+".mf"+" inesistente");
					}
					
					music2 = new MusicFile(main.getMfTrasposedLocation(),"v"+String.valueOf(x)+"_"+name+".mf");
					if(!music2.exists())
					{
						music2.createNewFile();
					}
					
					writer = new MusicFileWriter(new MusicWriter(music2));
					list = MusicFile.obtainEntities(music, oldscale);
					list2 = list.traspose(oldscale, newscale);
					writer.save(list2, false);
					writer.close();
				}
				
				Console.out.println(main.getMessages().getMessage(Messages.SUCCESS__FILE_TRASPOSED));
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
			catch (MusicException e) {
				e.printStackTrace();
			}
			
			Console.out.println();
			main.getConsole().pause();
		}
		while(finish);
		
	}

}
