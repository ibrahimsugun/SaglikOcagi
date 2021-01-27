package com.muaynetakip.Models;

import java.util.Date;

public class Appointment {
	private int id;
	private int patientId;
	private int doctorId;
	private int appointmentType;
	private String date;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(int appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}