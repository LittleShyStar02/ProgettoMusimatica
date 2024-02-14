package com.v1nc3nz0.musimathics.placeholders.enums;

/*
 * Variabili del menù di scelta
 */
public enum MenuChoice 
{
	
	OPERATION_A,
	OPERATION_B,
	OPERATION_C,
	OPERATION_D,
	OPERATION_E;
	
	@Override
	public String toString()
	{
		return "{"+name().toLowerCase()+"}";
	}
	
}
