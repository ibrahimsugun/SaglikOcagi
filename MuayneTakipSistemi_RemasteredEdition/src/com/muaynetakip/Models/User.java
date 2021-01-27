package com.muaynetakip.Models;

public class User {
	private int id;
	private String username;
	private String password;
	private Yetki yetki;
	
	public User() {
		if(this.getYetki() == null)
			this.setYetki(Yetki.HASTA);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Yetki getYetki() {
		return yetki;
	}
	public void setYetki(Yetki yetki) {
		this.yetki = yetki;
	}
	
}


