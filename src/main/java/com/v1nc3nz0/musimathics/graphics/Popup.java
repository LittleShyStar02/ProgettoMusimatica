package com.v1nc3nz0.musimathics.graphics;

import javax.swing.JOptionPane;

public class Popup
{
	
	public static void showSuccess(String message)
	{
		JOptionPane.showConfirmDialog(null, message,"${projectName}",JOptionPane.PLAIN_MESSAGE);
	}
	
	public static String showInput(String message)
	{
		return JOptionPane.showInputDialog(null,message,"${projectName}",JOptionPane.PLAIN_MESSAGE);
	}

}
