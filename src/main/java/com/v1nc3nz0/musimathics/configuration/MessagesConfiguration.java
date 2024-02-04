package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.configuration.enums.Messages;

import lombok.Getter;

public class MessagesConfiguration implements Configuration
{
	
	private YamlFile config; // file contenente i messaggi
	
	@Getter
	private String fileName;
	
	public MessagesConfiguration()
	{
		fileName = "messages.yml";
		load();
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
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getMessage(Messages path)
	{
		return config.getString(path.toString());
	}
	
	public List<String> getListMessage(Messages path)
	{
		return config.getStringList(path.toString());
	}

}
