package com.v1nc3nz0.musimathics.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Eccezione generale della musica
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@SuppressWarnings("serial")
public class MusicException extends Exception
{
	
	public String message;
	
}
