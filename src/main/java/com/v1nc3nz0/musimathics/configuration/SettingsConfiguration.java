package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.enums.Messages;
import com.v1nc3nz0.musimathics.io.Console;
import com.v1nc3nz0.musimathics.placeholders.Placeholder;

import lombok.Getter;

public class SettingsConfiguration implements Configuration
{
	
	private Musimathics main; // main instance
	private YamlFile config; // file contenente le impostazioni
	
	@Getter
	private String fileName; // nome del file
	
	public SettingsConfiguration(Musimathics main)
	{
		fileName = "settings.yml";
		this.main = main;
		load();
	}
	
	/*
	 * Ottieni la locazione dei file musicali
	 */
	public String getMusicFileLocation()
	{
		return config.getString("music-files-location");
	}
	
	/*
	 * Ottieni la locazione dei file di
	 * impostazioni dei file musicali
	 */
	public String getMFSettingsLocation()
	{
		return config.getString("music-files-settings-location");
	}
	
	/*
	 * Ottieni la locazione dei file
	 * musicali trasposti
	 */
	public String getMFTrasposedLocation()
	{
		return config.getString("music-traspose-location");
	}

	@Override
	public void load() 
	{
		File file = new File(fileName);
		try 
		{
			if(!file.exists()) ConfigManager.saveDefaults(null, fileName);
			config = new YamlFile(file);
			config.loadWithComments();
			main.getLogger().logs("Configurazione settings.yml caricata con successo");
		} 
		catch (IOException e) 
		{
			String message = main.getMessages().getMessage(Messages.ERROR__FILE_LOADING);
			message = Placeholder.replace("{data}",file.getPath().toString(), message);
			Console.out.println();
			main.getLogger().error(message);
		}
	}

}
