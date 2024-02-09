package com.v1nc3nz0.musimathics.musicfiles.entity;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class NoteList extends ArrayList<Note> implements MusicFileEntity
{
	
	public String obtain()
	{
		if(isEmpty()) return null;
		
		String str = "";
		
		for(Note note : this)
		{
			str = str.concat(note.obtain()+"+");
		}
		
		str = str.substring(0,str.length()-1);
		
		return str;
	}
	
	@Override
	public String toString()
	{
		if(isEmpty()) return null;
		
		String str = "NOTE ";
		
		for(Note note : this)
		{
			str = str.concat(note.toString()+" ");
		}
		
		str = str.substring(0,str.length()-1);
		
		return str;
		
	}

}
