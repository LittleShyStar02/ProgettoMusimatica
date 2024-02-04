package com.v1nc3nz0.musimathics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.v1nc3nz0.musimathics.configuration.MessagesConfiguration;
import com.v1nc3nz0.musimathics.configuration.SettingsConfiguration;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.io.ConsoleImpl;
import com.v1nc3nz0.musimathics.io.NativeC;
import com.v1nc3nz0.musimathics.menuselection.Operation;
import com.v1nc3nz0.musimathics.menuselection.a_Operation;

/*
 * Classe principale
 */
public class Musimathics
{
	
	private final static int EXIT_CODE = 27; // codice del tasto ESC
	private static Musimathics instance; // istanza attuale
	private Map<Character, Operation> operations; // mappa delle associazioni delle operazioni
	private SettingsConfiguration settings; // settings configuration
	private MessagesConfiguration messages; // messages configuration
	private File musicFileLocation; // cartella dei file musicali
	private NativeC nativec; // classe delle operazioni native c
	private Console console; // classe delle operazioni su console
	
	public Musimathics()
	{
		initialize();
		initializeOperations();
	}
	
	/*
	 * Inizializzazione iniziale del programma
	 */
	private void initialize()
	{
		Musimathics.instance = this;
		operations = new HashMap<>();
		
		settings = new SettingsConfiguration();
		messages = new MessagesConfiguration();
		
		musicFileLocation = new File(settings.getMusicFileLocation());
		if(!musicFileLocation.exists()) musicFileLocation.mkdir();
		
		nativec = new NativeC();
		console = new ConsoleImpl();
	}
	
	/*
	 * Inizializzazione delle operazioni
	 */
	private void initializeOperations()
	{
		operations.put('a', new a_Operation());
	}
	
	/*
	 * Ottieni l'istanza
	 */
	public static Musimathics getInstance()
	{
		return Musimathics.instance;
	}
	
	/*
	 * Ottieni l'istanza Console
	 */
	public Console getConsole()
	{
		return console;
	}
	
	/*
	 * Ottieni i messaggi del programma
	 */
	public MessagesConfiguration getMessages()
	{
		return messages;
	}
	
	/*
	 * Ottieni la cartella dei file musicali
	 */
	public File getMusicFileLocation()
	{
		return musicFileLocation;
	}
	
	/*
	 * Ottieni l'istanza native c
	 */
	public NativeC getNative()
	{
		return nativec;
	}
	
	/*
	 * Ottieni le impostazioni del programma
	 */
	public SettingsConfiguration getSettings()
	{
		return settings;
	}
	
	/*
	 * Menu Scelte Testuale
	 */
	private void menuChoice()
	{
		List<String> list = messages.getListMessage(Messages.MENU_CHOICE);
		for(String str : list)
		{
			
		}
	}
	
	/*
	 * Metodo dove viene eseguito il programma
	 */
	private void run() throws NumberFormatException, IOException 
	{
		Operation op;
		char selection;
		int choice;
		
		do
		{
			console.clear();
			menuChoice();
			choice = nativec.getch();
			if(choice != Musimathics.EXIT_CODE)
			{
				selection = (char) choice;
				if(operations.containsKey(selection))
				{
					op = operations.get(selection);
					op.run();
				}
			}
		}
		while(choice != Musimathics.EXIT_CODE);
	}
	
	/*
	 * Da qui parte l'intero programma
	 */
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Musimathics software = new Musimathics();
		software.run();
		software.getConsole().clear();
	}

}
