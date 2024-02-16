package com.v1nc3nz0.musimathics.configuration.enums;

/*
 * Variabili dei messaggi
 */
public enum Messages 
{
	
	DATA__FILE_SETTINGS,
	
	ERROR__FILE_ALREADY_EXISTS,
	ERROR__FILE_LOADING,
	ERROR__FILE_UNKNOWN,
	ERROR__IMPORT,
	ERROR__INPUT,
	ERROR__OPERATION_CANCELED,
	
	INPUT__FILE_CREATIONS,
	INPUT__FILE_SETTINGS,
	INPUT__NEW_FILE_SETTINGS,
	INPUT__IMPORT,
	
	OPERATIONS__MENU_CHOICE,
	OPERATIONS__A__DESCRIPTION,
	OPERATIONS__B__DESCRIPTION,
	OPERATIONS__C__DESCRIPTION,
	OPERATIONS__D__DESCRIPTION,
	OPERATIONS__E__DESCRIPTION,
	
	REASON__NULL_VALUE,
	
	SUCCESS__FILE_COPIED,
	SUCCESS__FILE_TRASPOSED,
	SUCCESS__IMPORT,
	SUCCESS__MUSIC_FILES_CREATED;
	
	@Override
	public String toString()
	{
		String name = name().toLowerCase();
		if(name.contains("__")) name = name.replace("__",".");
		if(name.contains("_")) name = name.replace("_", "-");
		return name;
	}
	
}
