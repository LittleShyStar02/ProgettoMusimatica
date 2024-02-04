package com.v1nc3nz0.musimathics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.io.ConsoleImpl;
import com.v1nc3nz0.musimathics.io.NativeC;
import com.v1nc3nz0.musimathics.menuselection.Operation;
import com.v1nc3nz0.musimathics.menuselection.a_Operation;

public class Musimathics
{
	
	private final static int EXIT_CODE = 27;
	private static Musimathics instance;
	private Map<Character, Operation> operations;
	private NativeC nativec;
	private Console console;
	
	public Musimathics()
	{
		console = new ConsoleImpl();
		nativec = new NativeC();
		Musimathics.instance = this;
		operations = new HashMap<>();
		initializeOperations();
	}
	
	private void initializeOperations()
	{
		operations.put('a', new a_Operation());
	}
	
	public static Musimathics getInstance()
	{
		return Musimathics.instance;
	}
	
	public Console getConsole()
	{
		return console;
	}
	
	public NativeC getNative()
	{
		return nativec;
	}
	
	private void menuChoice()
	{
		System.out.println("Benvenuto utente in questo software musicale");
		System.out.println();
		System.out.println("Premi il tasto vicino le operazioni per eseguirle");
		System.out.println();
		System.out.println("'a' - Operazione 1: Creazione di un file .mf");
		System.out.println("ESC - Esci dal programma");
	}
	
	private void run() throws NumberFormatException, IOException 
	{
		Operation op;
		char selection;
		int choise;
		
		do
		{
			console.clear();
			menuChoice();
			choise = nativec.getch();
			if(choise != Musimathics.EXIT_CODE)
			{
				selection = (char) choise;
				if(operations.containsKey(selection))
				{
					op = operations.get(selection);
					op.run();
				}
			}
		}
		while(choise != Musimathics.EXIT_CODE);
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
