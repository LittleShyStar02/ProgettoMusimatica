package com.v1nc3nz0.musimathics.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.v1nc3nz0.musimathics.Musimathics;

/*
 * Manager dei file
 */
public class ConfigManager 
{
	
	/*
	 * Permette di estrarre un file dal jar
	 */
	public static void saveDefaults(File folder,String name)
	{
		try {
			
			File file = new File(Musimathics.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			ZipFile zipfile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipfile.entries();
			
			ZipEntry entry;
			InputStream stream;
			while(entries.hasMoreElements())
			{
				entry = entries.nextElement();
				if(entry.getName().equals(name))
				{
					stream = zipfile.getInputStream(entry);
					if(folder == null)
						Files.copy(stream,new File(name).toPath());
					else
						Files.copy(stream, Paths.get(folder.getPath(), name));
				}
			}
			
			zipfile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
