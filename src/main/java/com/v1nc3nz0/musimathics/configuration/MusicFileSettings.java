package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.enums.MFSettings;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;

import lombok.Getter;

/*
 * File di impostazioni dei file musicali
 */
public class MusicFileSettings 
{
	
	private YamlFile config;
	
	@Getter
	private String fileName;
	
	public MusicFileSettings(String name)
	{
		load(null,name);
	}
	
	public MusicFileSettings(File dir,String name)
	{
		load(dir,name);
	}
	
	/*
	 * Ottieni i bpm del brano
	 */
	public int getBPM()
	{
		return config.getInt(MFSettings.MUSIC_BPM.toString());
	}
	
	/*
	 * Ottieni l'alterazione della nota della scala
	 */
	public Alteration getNoteAlteration()
	{
		return Alteration.valueOf(config.getString(MFSettings.MUSIC_SCALE__ALTERATION.toString()));
	}
	
	/*
	 * Ottieni la nota della scala
	 */
	public String getNoteScale()
	{
		return config.getString(MFSettings.MUSIC_SCALE__NOTE.toString());
	}
	
	/*
	 * Carica il file di configurazione
	 */
	private void load(File dir,String name)
	{
		File file;
		if(dir == null) file = new File(name);
		else file = new File(dir,name);
		try {
			
			if(!file.exists()) ConfigManager.saveDefaults(dir, name);
			
			config = new YamlFile(file);
			config.loadWithComments();
			//Musimathics.getInstance().getLogger().append("File " + file.getPath().toString() + " caricato con successo");
		} catch (IOException e) {
			//Musimathics.getInstance().getLogger().error("Errore durante il caricamento del file " + file.getPath().toString());
		}
	}
	

}
