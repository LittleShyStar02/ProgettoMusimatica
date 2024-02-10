package com.v1nc3nz0.musimathics.musicfiles.enums;

import com.v1nc3nz0.musimathics.exceptions.InvalidNoteException;

import lombok.Getter;

/*
 * Nome delle note
 */
public enum NoteName
{
	A0("LA0"),B0("SI0"),
	C1("DO1"),D1("RE1"),E1("MI1"),F1("FA1"),G1("SOL1"),A1("LA1"),B1("SI1"),
	C2("DO2"),D2("RE2"),E2("MI2"),F2("FA2"),G2("SOL2"),A2("LA2"),B2("SI2"),
	C3("DO3"),D3("RE3"),E3("MI3"),F3("FA3"),G3("SOL3"),A3("LA3"),B3("SI3"),
	C4("DO4"),D4("RE4"),E4("MI4"),F4("FA4"),G4("SOL4"),A4("LA4"),B4("SI4"),
	C5("DO5"),D5("RE5"),E5("MI5"),F5("FA5"),G5("SOL5"),A5("LA5"),B5("SI5"),
	C6("DO6"),D6("RE6"),E6("MI6"),F6("FA6"),G6("SOL6"),A6("LA6"),B6("SI6"),
	C7("DO7"),D7("RE7"),E7("MI7"),F7("FA7"),G7("SOL7"),A7("LA7"),B7("SI7"),
	C8("DO8");
	
	@Getter
	private String italianName;
	
	NoteName(String italianName)
	{
		this.italianName = italianName;
	}
	
	/*
	 * Converte il nome della stringa in costante dell'enum
	 */
	public static NoteName toName(String value) throws InvalidNoteException
	{
		for(NoteName name : NoteName.values())
		{
			if(name.name().equals(value) || name.getItalianName().equals(value)) return name;
		}
		throw new InvalidNoteException(value + " non riconosiuto come nome nota"); 
	}
}