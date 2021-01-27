package com.muaynetakip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GirdiIslemleri {
	
	public static String girdi() throws IOException {
		BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in)); 
		
		String input = reader.readLine();
		return input;
	}
	
}
