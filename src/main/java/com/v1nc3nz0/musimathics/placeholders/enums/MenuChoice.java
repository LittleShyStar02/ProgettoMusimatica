package com.v1nc3nz0.musimathics.placeholders.enums;

/*
 * Variabili del menù di scelta
 */
public enum MenuChoice 
{
	
	OPERATION_A;
	
	@Override
	public String toString()
	{
		return "{"+name().toLowerCase()+"}";
	}
	
}
