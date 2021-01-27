package com.muaynetakip.Repository.Concrete;

import static java.lang.System.out;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muaynetakip.Models.Appointment;
import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Models.Patient;
import com.muaynetakip.Repository.Abstract.IRepository;
import com.muaynetakip.Repository.Abstract.RepositoryBase;

public class AppointmentRepository extends RepositoryBase implements IRepository<Appointment> {
	
	/*
	 * 
	 * 	private int id;
		private int patientId;
		private int doctorId;
		private int appointmentType;
		private String date;
		private double price;
	 * 
	 * */
	
	public static AppointmentRepository Instance;
	
	public AppointmentRepository() throws SQLException {
		super();
		if(Instance == null)
			Instance = this;
		
		// Debug amaciyla tabloyu siler!
		//Statement statementZero = connection.createStatement();
		//statementZero.executeUpdate("DROP TABLE IF EXISTS appointments;");
		// Veritabanindaki tabloyu yaratir!
		
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ "appointments("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " patientId INTEGER,"
				+ " doctorId INTEGER,"
				+ " appointmentType INTEGER,"
				+ " date TEXT"
				+ ");";
		statement.executeUpdate(sql);
		
	}

	@Override
	public void add(Appointment data) throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "INSERT INTO appointments"
				+ "(patientId, doctorId, appointmentType, date) VALUES ("
				+ data.getPatientId()+ ","
				+  data.getDoctorId() + ","
				+ "'" + data.getAppointmentType() + "'" + ","
				+ "'" + data.getDate() + "'"
				+ ");";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili, 0 - basarisiz): " + status);
	}

	@Override
	public void update(int id, Appointment data) throws Exception {
		// TODO Auto-generated method stub
				Statement statement = connection.createStatement();
				String sql = "UPDATE appointments SET "
						+ "'" + data.getPatientId() + "'" + ","
						+  "'" + data.getDoctorId() + "'" + ","
						+ "'" + data.getAppointmentType() + "'" + ","
						+ data.getDate()
						+ " WHERE id = " + id + ";";
				out.println(sql);
				int status = statement.executeUpdate(sql);
				out.println("Sorgu durumu (1 - basarili, 0 - basarisiz): " + status);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appointment> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM appointments";
		
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		while(rs.next()) {
			Appointment appointment = new Appointment();
			int id = rs.getInt(1);
			int patientId = rs.getInt(2);
			int doctorId = rs.getInt(3);
			int appointmentType = rs.getInt(4);
			String date = rs.getString(5);
			
			appointment.setId(id);
			appointment.setPatientId(patientId);
			appointment.setDoctorId(doctorId);
			appointment.setAppointmentType(appointmentType);
			appointment.setDate(date);
			
			appointments.add(appointment);
		}
		return appointments;
	}
	
	public List<Appointment> fetchAllByPatientId(int patientId) throws Exception {
		// TODO Auto-generated method stub
		Patient patient = PatientRepository.Instance.fetchById(patientId);
		
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM appointments WHERE patientId = " + patient.getPatientId() + ";";
		
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		while(rs.next()) {
			Appointment appointment = new Appointment();
			int id = rs.getInt(1);
			patientId = rs.getInt(2);
			int doctorId = rs.getInt(3);
			int appointmentType = rs.getInt(4);
			String date = rs.getString(5);
			
			appointment.setId(id);
			appointment.setPatientId(patientId);
			appointment.setDoctorId(doctorId);
			appointment.setAppointmentType(appointmentType);
			appointment.setDate(date);
			
			appointments.add(appointment);
		}
		return appointments;
		
	}
	
	public List<Appointment> fetchAllByDoctorId(int doctorId) throws Exception {
		// TODO Auto-generated method stub
		Doctor doctor = DoctorRepository.Instance.fetchById(doctorId);
		
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM appointments WHERE doctorId = " + doctor.getId() + ";";
		
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		while(rs.next()) {
			Appointment appointment = new Appointment();
			int id = rs.getInt(1);
			int patientId = rs.getInt(2);
			doctorId = rs.getInt(3);
			int appointmentType = rs.getInt(4);
			String date = rs.getString(5);
			
			appointment.setId(id);
			appointment.setPatientId(patientId);
			appointment.setDoctorId(doctorId);
			appointment.setAppointmentType(appointmentType);
			appointment.setDate(date);
			
			appointments.add(appointment);
		}
		return appointments;
	}

	@Override
	public Appointment fetchById(int id) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM appointments WHERE id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		Appointment appointment = new Appointment();
		
		while(rs.next()) {
			id = rs.getInt(1);
			int patientId = rs.getInt(2);
			int doctorId = rs.getInt(3);
			int appointmentType = rs.getInt(4);
			String date = rs.getString(5);
			
			appointment.setId(id);
			appointment.setPatientId(patientId);
			appointment.setDoctorId(doctorId);
			appointment.setAppointmentType(appointmentType);
			appointment.setDate(date);
		}
		return appointment;
	}

}
