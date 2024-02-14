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
	private Musimathics main;
	
	public static PrintStream err;
	public static PrintStream out;
	public static PrintStream voiderror;
	public static PrintStream voidout;
	
	public Console(Musimathics main)
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		this.main = main;
		
		Console.voiderror =  new PrintStream(new OutputStream() {
		    @Override
		    public void write(int b) {}
		});
		Console.voidout = new PrintStream(new OutputStream() {
		    @Override
		    public void write(int b) {}
		});
		Console.err = System.err;
		Console.out = System.out;
	}

	public void clear() 
	{
		main.getNativeC().system("cls");
	}
	
	public void pause()
	{
		main.getNativeC().system("pause");
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
