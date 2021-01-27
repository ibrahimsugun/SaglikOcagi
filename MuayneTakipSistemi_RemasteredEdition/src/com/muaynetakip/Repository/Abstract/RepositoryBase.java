package com.muaynetakip.Repository.Abstract;
import java.sql.Connection;
import java.sql.DriverManager;
public class RepositoryBase {
	
	protected static Connection connection;
	
	protected RepositoryBase() {
		connect();
	}
	
	public static void main(String[] args) {
	}
	
	private static void connect() {
	      try {
	         Class.forName("org.sqlite.JDBC");
	         connection = DriverManager.getConnection("jdbc:sqlite:hastane.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}
}
