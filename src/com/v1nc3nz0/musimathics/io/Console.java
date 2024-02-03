package com.v1nc3nz0.musimathics.io;

import java.io.IOException;

public interface Console 
{
	
	public byte readByte() throws NumberFormatException, IOException;
	public char readChar() throws IOException;
	public double readDouble() throws NumberFormatException, IOException ;
	public float readFloat() throws NumberFormatException, IOException ;
	public int readInt() throws NumberFormatException, IOException;
	public String readLine() throws IOException;
	public long readLong() throws NumberFormatException, IOException;
	public short readShort() throws NumberFormatException, IOException;
	
}
