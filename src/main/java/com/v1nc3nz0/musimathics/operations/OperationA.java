package com.v1nc3nz0.musimathics.operations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.ConfigManager;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.graphics.Popup;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

public class OperationA implements Operation
{

	private Musimathics main;
	
	public OperationA(Musimathics main)
	{
		this.main = main;
	}
	
	@Override
	public void run() 
	{
		String message = null,outmessage;
		boolean finish = true;
		
		String name;
		File source, target, target2;
		
		do
		{
			
			try {
				main.getConsole().clear();
				Console.out.println(" ---------------------------------------");
				Console.out.println("|                                       |");
				Console.out.println("|             Operazione A              |");
				Console.out.println("|                                       |");
				Console.out.println(" ---------------------------------------");
				Console.out.println("\n");
				
				main.getLogger().logs(" ---------------------------------------");
				main.getLogger().logs("|                                       |");
				main.getLogger().logs("|             Operazione A              |");
				main.getLogger().logs("|                                       |");
				main.getLogger().logs(" ---------------------------------------");
				
				
				if(message != null)
				{
					Console.out.println(message);
					main.getLogger().error(message);
					message = null;
				}
				
				name = Popup.showInput(main.getMessages().getMessage(Messages.INPUT__FILE_CREATIONS));
				if(name == null || name.isEmpty())
				{
					message = main.getMessages().getMessage(Messages.ERROR__INPUT);
					message = Placeholder.replace("{data}", main.getMessages().getMessage(Messages.DATA__FILE_SETTINGS), message);
					message = Placeholder.replace("{reason}", main.getMessages().getMessage(Messages.REASON__NULL_VALUE), message);
					continue;
				}
				
				target = new File(main.getMfSettingsLocation(),name+".yml");
				if(target.exists())
				{
					message = main.getMessages().getMessage(Messages.ERROR__FILE_ALREADY_EXISTS);
					message = Placeholder.replace("{data}", name+".yml", message);
					continue;
				}
				else
				{
					source = new File("example.yml");
					if(!source.exists()) ConfigManager.saveDefaults(null, source.getName());
					Files.copy(source.toPath(), target.toPath());
					source.delete();
					
					target2 = new File(main.getMusicFileLocation(),name+".mf");
					if(target2.exists())
					{
						message = main.getMessages().getMessage(Messages.ERROR__FILE_ALREADY_EXISTS);
						message = Placeholder.replace("{data}", name+".mf", message);
						target.delete();
						continue;
					}
					else
					{
						source = new File("example.mf");
						if(!source.exists()) ConfigManager.saveDefaults(null, source.getName());
						Files.copy(source.toPath(), target2.toPath());
						source.delete();
						
						outmessage = main.getMessages().getMessage(Messages.SUCCESS__MUSIC_FILES_CREATED);
						outmessage = Placeholder.replace("{musicfile}", name+".mf",outmessage);
						outmessage = Placeholder.replace("{musicfilesettings}", name+".yml",outmessage);
						Console.out.println(outmessage);
						main.getLogger().logs(outmessage);
						
						finish = false;
						
						Console.out.println("\nInfo per modificare il file musicale");
						Console.out.println("\nBAR -> rappresenta una battuta");
						Console.out.println("\nNOTE <nota>;<durata>[;<alterazione>] -> rappresenta una nota");
						Console.out.println("Esempio NOTE MI5;q o MI5;q;b");
						Console.out.println("\nPAUSA <durata> -> rappresenta una pausa");
						Console.out.println("Esempio PAUSE q");
						Console.out.println("\nSe più note devono essere suonate contemporaneamente mettere i valori uno dopo l'altro");
						Console.out.println("Esempio NOTE DO4;q MI4;q SOL4;q");
					}
							
				}
				
			} 
			catch (IOException e) 
			{
				Console.out.println("Errore durante il processamento di un file");
			}
			
			Console.out.println();
			main.getConsole().pause();
		}
		while(finish);
		
	}

}
