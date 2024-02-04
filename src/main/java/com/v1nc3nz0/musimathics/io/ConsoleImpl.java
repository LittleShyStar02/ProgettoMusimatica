package com.v1nc3nz0.musimathics.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.v1nc3nz0.musimathics.Musimathics;

/*
 * Classe di input associata all'interfaccia console
 */
public class ConsoleImpl implements Console
{
	
	private BufferedReader reader;
	
	public ConsoleImpl()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void clear() 
	{
		Musimathics.getInstance().getNative().system("cls");
	}
	
	@Override
	public byte readByte() throws NumberFormatException, IOException
	{
		return Byte.parseByte(readLine());
	}

	@Override
	public char readChar() throws IOException 
	{
		return readLine().charAt(0);
	}

	@Override
	public double readDouble() throws NumberFormatException, IOException 
	{
		return Double.parseDouble(readLine());
	}

	@Override
	public float readFloat() throws NumberFormatException, IOException 
	{
		return Float.parseFloat(readLine());
	}

	@Override
	public int readInt() throws NumberFormatException, IOException 
	{
		return Integer.parseInt(readLine());
	}
	
	@Override
	public String readLine() throws IOException 
	{
		return reader.readLine();
	}


	@Override
	public long readLong() throws NumberFormatException, IOException 
	{
		return Long.parseLong(readLine());
	}

	@Override
	public short readShort() throws NumberFormatException, IOException 
	{
		return Short.parseShort(readLine());
	}

}
