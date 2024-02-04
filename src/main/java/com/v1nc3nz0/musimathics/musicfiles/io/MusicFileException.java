package com.v1nc3nz0.musimathics.musicfiles.io;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Eccezione generale del file musicale
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@SuppressWarnings("serial")
public class MusicFileException extends IOException
{
	
	String message;
	
}
