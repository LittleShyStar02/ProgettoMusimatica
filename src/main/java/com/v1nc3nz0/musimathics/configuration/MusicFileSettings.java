package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;

import org.simpleyaml.configuration.file.YamlFile;

import com.v1nc3nz0.musimathics.Musimathics;
import com.v1nc3nz0.musimathics.configuration.enums.MFSettings;
import com.v1nc3nz0.musimathics.enums.ScaleType;
import com.v1nc3nz0.musimathics.musicfiles.enums.Alteration;
import com.v1nc3nz0.musimathics.musicfiles.enums.NoteName;

import lombok.Getter;

/*
 * File di impostazioni dei file musicali
 */
public class MusicFileSettings 
{
	
	private Musimathics main; // main instance
	private YamlFile config; // configuration
	
	@Getter
	private String fileName; // nome del file
	
	public MusicFileSettings(String name, Musimathics main)
	{
		this.main = main;
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
	 * Ottieni i nomi delle voci
	 */
	public int getVoices()
	{
		return config.getInt(MFSettings.VOICE_NUMBERS.toString());
	}
	
	/*
	 * Ottieni la nota della scala
	 */
	public NoteName getNoteScale()
	{
		return NoteName.valueOf(config.getString(MFSettings.MUSIC_SCALE__NOTE.toString())+"1");
	}
	
	/*
	 * Ottieni il tipo di scala
	 */
	public ScaleType getScaleType()
	{
		return ScaleType.valueOf(config.getString(MFSettings.MUSIC_SCALE__TYPE.toString()));
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
			main.getLogger().append("File " + file.getPath().toString() + " caricato con successo");
		} catch (IOException e) {
			main.getLogger().error("Errore durante il caricamento del file " + file.getPath().toString());
		}
	}
	

}
