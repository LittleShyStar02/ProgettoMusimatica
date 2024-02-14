package com.v1nc3nz0.musimathics.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

import com.v1nc3nz0.musimathics.io.Console;

import lombok.Getter;

public class Logger 
{
	
	@Getter
	private File latestLogs;  // file log
	
	private BufferedWriter writer;

	public Logger()
	{
		try 
		{
			latestLogs = new File("latest.log");
			if(latestLogs.exists()) latestLogs.delete();
			latestLogs.createNewFile();
			writer = new BufferedWriter(new FileWriter(latestLogs,true));
		} catch (IOException e) {
			Console.err.println("Errore durante l'inizializzazione del logger");
		}
		
	}
	
	/*
	 * Scrive nel logs file un qualsiasi messaggio
	 */
	public void append(String message)
	{
		try {
			LocalTime date = LocalTime.now();
			writer.append("[" + date.getHour() + ":" + date.getMinute() + ":" + date.getSecond() + "] " + message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Scrive nel logs un messaggio di errore
	 */
	public void error(String message)
	{
		append("[ERROR] " + message);
	}
	
	/*
	 * Ditrugge il writer insieme all'oggetto
	 */
	@Override
	public void finalize()
	{
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Scrive un log di priorità normale
	 */
	public void logs(String message)
	{
		append("[NORMAL] " + message);
	}
	
	/*
	 * Inizializza il logger
	 */
	public void setupLogger()
	{
			try 
			{
				if(latestLogs.exists()) latestLogs.delete();
				latestLogs.createNewFile();
				writer = new BufferedWriter(new FileWriter(latestLogs,true));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * Scrive nel logs un warnings
	 */
	public void warn(String message)
	{
		append("[WARN] " + message);
	}
	
}
