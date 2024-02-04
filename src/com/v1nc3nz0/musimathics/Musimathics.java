package com.v1nc3nz0.musimathics;

import java.io.IOException;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.io.InputConsole;
import com.v1nc3nz0.musimathics.io.NativeC;

public class Musimathics 
{
	
	private final static int EXIT_CODE = 2;
	private static Musimathics instance;
	private NativeC nativec;
	private Console console;
	
	public Musimathics()
	{
		console = new InputConsole();
		nativec = new NativeC();
		Musimathics.instance = this;
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
		System.out.println("A - Operazione 1: Creazione di un file .mf");
		System.out.println("B - Operazione 2: Lettura di un file .mf");
		System.out.println("ESC - Esci dal programma");
	}
	
	private void run() throws NumberFormatException, IOException 
	{
		int choise;
		
		do
		{
			menuChoice();
			choise = nativec.getch();
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
		
	}

}
