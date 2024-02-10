package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.Musimathics;

import lombok.Getter;

public class SettingsConfiguration implements Configuration
{
	
	private YamlFile config; // file contenente le impostazioni
	
	@Getter
	private String fileName;
	
	public SettingsConfiguration()
	{
		fileName = "settings.yml";
		load();
	}
	
	public String getMFSettingsLocation()
	{
		return config.getString("music-files-settings-location");
	}
	
	public String getMusicFileLocation()
	{
		return config.getString("music-files-location");
	}

	@Override
	public void load() 
	{
		try 
		{
			File file = new File(fileName);
			if(!file.exists()) ConfigManager.saveDefaults(null, fileName);
			config = new YamlFile();
			config.loadWithComments();
			Musimathics.getInstance().getLogger().logs("Configurazione settings.yml caricata con successo");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Musimathics.getInstance().getLogger().error(e.getMessage());
		}
	}

}
