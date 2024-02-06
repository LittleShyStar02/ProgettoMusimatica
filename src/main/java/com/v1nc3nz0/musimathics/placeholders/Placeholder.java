package com.v1nc3nz0.musimathics.placeholders;

public class Placeholder 
{
	
	/*
	 * Rimpiazza nel messaggio la 'placeholder' con 'value'
	 * se il messaggio la contiene
	 */
	public static String replace(String placeholder,String value,String message)
	{
		if(message.contains(placeholder))
			message = message.replace(placeholder, value);
		return message;
	}

}
