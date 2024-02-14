package com.v1nc3nz0.musimathics.menuselection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.musicfiles.exceptions.InvalidMusicFileException;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

public class OperationC implements Operation 
{

	private Musimathics main;
	
	public OperationC(Musimathics main)
	{
		this.main = main;
	}
	
	@Override
	public void run() 
	{
		
		try {
			main.getConsole().clear();
			Console.out.println(" ---------------------------------------");
			Console.out.println("|                                       |");
			Console.out.println("|             Operazione C              |");
			Console.out.println("|                                       |");
			Console.out.println(" ---------------------------------------");
			Console.out.println("\nCaricamento in corso...");
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Music Files", "mf", "yml");
			JFileChooser chooser = new JFileChooser();
			File source,target=null;
			
			chooser.setCurrentDirectory(main.getMfTrasposedLocation());
			chooser.setFileFilter(filter);
			
			Console.out.println(main.getMessages().getMessage(Messages.INPUT__IMPORT));
			if(chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			{
				Console.out.println(main.getMessages().getMessage(Messages.ERROR__OPERATION_CANCELED));
			}
			else
			{
				source = chooser.getSelectedFile();
				
				if(source.getName().endsWith(".mf")) target = new File(main.getMusicFileLocation(),source.getName());
				
				if(source.getName().endsWith(".yml")) target = new File(main.getMfSettingsLocation(),source.getName());
				
				if(target == null) throw new InvalidMusicFileException(main.getMessages().getMessage(Messages.ERROR__FILE_UNKNOWN));
				
				Files.copy(source.toPath(), target.toPath());
				String outmessage = main.getMessages().getMessage(Messages.SUCCESS__IMPORT);
				outmessage = Placeholder.replace("{filename}", target.getName(), outmessage);
				Console.out.println(outmessage);
			}
			
		}
		catch (IOException e) 
		{
			Console.out.println(main.getMessages().getMessage(Messages.ERROR__IMPORT));
		} catch (InvalidMusicFileException e) 
		{
			Console.out.println(e.getMessage());
		}
		
		Console.out.println();
		main.getConsole().pause();
		
	}

}
