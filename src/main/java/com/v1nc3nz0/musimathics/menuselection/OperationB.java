package com.v1nc3nz0.musimathics.menuselection;

import java.io.File;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.ConfigManager;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.io.Console;

public class OperationB implements Operation
{

	private Musimathics main;
	
	public OperationB(Musimathics main)
	{
		this.main = main;
	}
	
	@Override
	public void run() 
	{
		main.getConsole().clear();
		Console.out.println(" ---------------------------------------");
		Console.out.println("|                                       |");
		Console.out.println("|             Operazione B              |");
		Console.out.println("|                                       |");
		Console.out.println(" ---------------------------------------");
		Console.out.println("\n");
		
		File file = new File("example.yml");
		if(!file.exists()) ConfigManager.saveDefaults(null, "example.yml");
		file = new File("example.mf");
		if(!file.exists()) ConfigManager.saveDefaults(null, "example.mf");
		
		Console.out.println(main.getMessages().getMessage(Messages.SUCCESS__FILE_COPIED));
		
		Console.out.println();
		main.getConsole().pause();
		
	}

}
