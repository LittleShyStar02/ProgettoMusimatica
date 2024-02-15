package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

import lombok.Getter;

/*
 * Configuration dei messaggi
 */
public class MessagesConfiguration implements Configuration
{
	
	private Musimathics main; // main instance
	
	private YamlFile config; // file contenente i messaggi
	
	@Getter
	private String fileName; // noem del file dei messaggi
	
	public MessagesConfiguration(Musimathics main)
	{
		fileName = "messages.yml";
		this.main = main;
		load();
	}

	/*
	 * Carica la configurazione
	 */
	@Override
	public void load() 
	{
		File file = new File(fileName);;
		try 
		{
			if(!file.exists()) ConfigManager.saveDefaults(null, fileName);
			config = new YamlFile(file);
			config.loadWithComments();
			main.getLogger().logs("Configurazione messages.yml caricata con successo");
		} 
		catch (IOException e) 
		{
			String message = main.getMessages().getMessage(Messages.ERROR__FILE_LOADING);
			message = Placeholder.replace("{data}",file.getPath().toString(), message);
			Console.out.println();
			main.getLogger().error(message);
		}
	}
	
	/*
	 * Ti permette di ottenere il messaggio dal path
	 */
	public String getMessage(Messages path)
	{
		return config.getString(path.toString());
	}
	
	/*
	 * Ti permette di ottenere un messaggio
	 * scritto su più righe
	 */
	public List<String> getListMessage(Messages path)
	{
		return config.getStringList(path.toString());
	}

}
