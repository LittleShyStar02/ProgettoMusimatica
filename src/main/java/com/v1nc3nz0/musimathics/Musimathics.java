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
import com.v1nc3nz0.musimathics.io.NativeC;
import com.v1nc3nz0.musimathics.logger.Logger;
import com.v1nc3nz0.musimathics.menuselection.Operation;
import com.v1nc3nz0.musimathics.menuselection.OperationA;
import com.v1nc3nz0.musimathics.menuselection.OperationB;
import com.v1nc3nz0.musimathics.menuselection.OperationC;
import com.v1nc3nz0.musimathics.menuselection.OperationD;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;
import com.v1nc3nz0.musimathics.placeholders.enums.MenuChoice;

import lombok.Getter;

/*
 * Classe principale
 */
public class Musimathics
{
	
	private final static int EXIT_CODE = 27; // codice del tasto ESC
	
	private Map<Character, Operation> operations; // mappa delle associazioni delle operazioni
	
	@Getter
	private File musicFileLocation; // cartella dei file musicali
	
	@Getter
	private File mfSettingsLocation; // cartella delle impostazioni dei file musicali
	
	@Getter
	private File mfTrasposedLocation; // cartella dei file trasposti
	
	@Getter
	private SettingsConfiguration settings; // settings configuration
	
	@Getter
	private MessagesConfiguration messages; // messages configuration
	
	@Getter
	private Logger logger; // classe del logger
	
	@Getter
	private NativeC nativeC; // classe delle operazioni native c
	
	@Getter
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
		operations = new HashMap<>();
		
		settings = new SettingsConfiguration(this);
		messages = new MessagesConfiguration(this);
		
		musicFileLocation = new File(settings.getMusicFileLocation());
		musicFileLocation.mkdirs();
		
		mfSettingsLocation = new File(settings.getMFSettingsLocation());
		mfSettingsLocation.mkdirs();
		
		mfTrasposedLocation = new File(settings.getMFTrasposedLocation());
		mfTrasposedLocation.mkdirs();
		
		nativeC = new NativeC();
		console = new Console(this);
		getLogger().logs("Inizializzazione dati completata con successo");
	}
	
	/*
	 * Inizializzazione delle operazioni
	 */
	private void initializeOperations()
	{
		getLogger().logs("Inizializzazione operazioni menù scelta");
		operations.put('a', new OperationA(this));
		operations.put('b', new OperationB(this));
		operations.put('c', new OperationC(this));
		operations.put('d', new OperationD(this));
		getLogger().logs("Operazioni del menù di selezione inizializzate con successo");
	}
	
	/*
	 * Menu Scelte Testuale
	 */
	private void menuChoice()
	{
		List<String> list = getMessages().getListMessage(Messages.OPERATIONS__MENU_CHOICE);
		for(String str : list)
		{
			String replace;
			for(Character ch : operations.keySet())
			{
				replace = String.valueOf(ch);
				if(str.contains("{operation_"+replace+"}")) 
					str = Placeholder.replace(MenuChoice.valueOf("OPERATION_"+replace.toUpperCase()).toString(), 
							getMessages().getMessage(Messages.valueOf("OPERATIONS__"+replace.toUpperCase()+"__DESCRIPTION")),str);
			}
			Console.out.println(str);
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
			choice = getNativeC().getch();
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
