package com.v1nc3nz0.musimathics.io;

import java.io.File;

import com.v1nc3nz0.musimathics.configuration.ConfigManager;

/*
 * Funzioni richiamate dal codice nativo c
 */
public class NativeC 
{
	
	static 
	{
		String name = "ProgettoMusimatica.dll";
		File file = new File(name);
		if(!file.exists()) ConfigManager.saveDefaults(null, name);
		System.loadLibrary("ProgettoMusimatica");
	}
	
	public native int getch();
	public native void system(String command);

}
