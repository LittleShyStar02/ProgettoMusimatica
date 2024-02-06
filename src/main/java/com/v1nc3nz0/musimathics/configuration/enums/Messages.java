package com.v1nc3nz0.musimathics.configuration.enums;

public enum Messages 
{

	MENU_CHOICE,
	OPERATIONS__A_DESCRIPTION;
	
	@Override
	public String toString()
	{
		String name = name().toLowerCase();
		if(name.contains("__")) name = name.replace("__",".");
		if(name.contains("_")) name = name.replace("_", "-");
		return name;
	}
	
}
