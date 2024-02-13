package com.v1nc3nz0.musimathics.musicfiles.exceptions;

import com.v1nc3nz0.musimathics.exceptions.MusicException;

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
public class MusicFileException extends MusicException
{
	
	String message;
	
}
