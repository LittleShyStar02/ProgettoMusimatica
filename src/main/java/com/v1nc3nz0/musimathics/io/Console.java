package com.v1nc3nz0.musimathics.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import com.v1nc3nz0.musimathics.Musimathics;

/*
 * Classe associata all'interfaccia console
 */
public class Console
{
	
	private BufferedReader reader;
	public static PrintStream CONSOLE_OUT;
	public static PrintStream NO_OUT;
	
	public Console()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		Console.NO_OUT = new PrintStream(new OutputStream() {
		    @Override
		    public void write(int b) {}
		});
		Console.CONSOLE_OUT = System.out;
	}

	public void clear() 
	{
		Musimathics.getInstance().getNative().system("cls");
	}
	
	public void pause()
	{
		Musimathics.getInstance().getNative().system("pause");
	}
	
	public byte readByte() throws NumberFormatException, IOException
	{
		return Byte.parseByte(readLine());
	}

	public char readChar() throws IOException 
	{
		return readLine().charAt(0);
	}

	public double readDouble() throws NumberFormatException, IOException 
	{
		return Double.parseDouble(readLine());
	}

	public float readFloat() throws NumberFormatException, IOException 
	{
		return Float.parseFloat(readLine());
	}

	public int readInt() throws NumberFormatException, IOException 
	{
		return Integer.parseInt(readLine());
	}
	
	public String readLine() throws IOException 
	{
		return reader.readLine();
	}


	public long readLong() throws NumberFormatException, IOException 
	{
		return Long.parseLong(readLine());
	}

	public short readShort() throws NumberFormatException, IOException 
	{
		return Short.parseShort(readLine());
	}

}
