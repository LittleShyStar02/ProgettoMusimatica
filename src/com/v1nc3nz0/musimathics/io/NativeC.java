package com.v1nc3nz0.musimathics.io;

/*
 * Funzioni richiamate dal codice nativo c
 */
public class NativeC 
{
	
	static 
	{
		System.loadLibrary("ProgettoMusimatica");
	}
	
	public native int getch();
	public native void system(String command);

}
