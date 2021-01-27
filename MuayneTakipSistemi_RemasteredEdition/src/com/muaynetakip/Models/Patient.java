package com.muaynetakip.Models;

import java.util.Date;

public class Patient {
	
	private int patientId;
	private String name;
	private String lastName;
	private int gender;
	private int healthInsurance;
	private String birthDate;
	private int isStudent;
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(int healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public int getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}
}
