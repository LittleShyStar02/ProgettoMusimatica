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
import com.v1nc3nz0.musimathics.logger.Logger;
import com.v1nc3nz0.musimathics.menuselection.Operation;
import com.v1nc3nz0.musimathics.menuselection.a_Operation;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;
import com.v1nc3nz0.musimathics.placeholders.enums.MenuChoice;

/*
 * Classe principale
 */
public class Musimathics
{
	
	private final static int EXIT_CODE = 27; // codice del tasto ESC
	
	private static Musimathics instance; // istanza attuale
	
	private Map<Character, Operation> operations; // mappa delle associazioni delle operazioni
	private File musicFileLocation; // cartella dei file musicali
	
	private SettingsConfiguration settings; // settings configuration
	private MessagesConfiguration messages; // messages configuration
	
	private Logger logger; // classe del logger
	private NativeC nativec; // classe delle operazioni native c
	private Console console; // classe delle operazioni su console
	
	public Musimathics()
	{
		logger = new Logger();
		initialize();
		initializeOperations();
	}
	
	/*
	 * Inizializzazione iniziale del programma
	 */
	private void initialize()
	{
		getLogger().logs("Inizializzazione dei dati");
		Musimathics.instance = this;
		operations = new HashMap<>();
		
		settings = new SettingsConfiguration();
		messages = new MessagesConfiguration();
		
		musicFileLocation = new File(settings.getMusicFileLocation());
		if(!musicFileLocation.exists()) 
		{
			musicFileLocation.mkdir();
			logger.logs("Cartella dei file musicali non trovata. L'abbiamo creata noi per te!");
		}
		
		nativec = new NativeC();
		console = new ConsoleImpl();
		getLogger().logs("Inizializzazione dati completata con successo");
	}
	
	/*
	 * Inizializzazione delle operazioni
	 */
	private void initializeOperations()
	{
		getLogger().logs("Inizializzazione operazioni menù scelta");
		operations.put('a', new a_Operation());
		getLogger().logs("Operazioni del menù di selezione inizializzate con successo");
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
	 * Ottieni il logger
	 */
	public Logger getLogger()
	{
		return logger;
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
		List<String> list = getMessages().getListMessage(Messages.MENU_CHOICE);
		for(String str : list)
		{
			for(Character ch : operations.keySet())
			{
				if(str.contains("{operation_"+String.valueOf(ch)+"}")) 
					str = Placeholder.replace(MenuChoice.OPERATION_A.toString(), 
							getMessages().getMessage(Messages.OPERATIONS__A_DESCRIPTION), str);
			}
			System.out.println(str);
		}
	}
	
	/*
	 * Metodo dove viene eseguito il programma
	 */
	private void run() throws NumberFormatException, IOException 
	{
		getLogger().logs("Inizio esecuzione software");
		Operation op;
		char selection;
		int choice;
		
		do
		{
			getConsole().clear();
			menuChoice();
			choice = getNative().getch();
			if(choice != Musimathics.EXIT_CODE)
			{
				selection = (char) choice;
				if(operations.containsKey(selection))
				{
					op = operations.get(selection);
					getLogger().logs("Inizio esecuzione operazione " + selection);
					op.run();
					getLogger().logs("Termine esecuzione operazione " + selection);
				}
			}
		}
		while(choice != Musimathics.EXIT_CODE);
		getLogger().logs("Termine esecuzione software");
	}
	
	/*
	 * Da qui parte l'intero programma
	 */
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Musimathics software = new Musimathics();
		software.getLogger().logs("Software avviato correttamente");
		software.run();
		software.getLogger().logs("Software fermato correttamente");
		software.getLogger().finalize();
		software.getConsole().clear();
	}

}
