package com.muaynetakip.Repository.Concrete;
import static java.lang.System.out;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Models.Patient;
import com.muaynetakip.Repository.Abstract.IRepository;
import com.muaynetakip.Repository.Abstract.RepositoryBase;

public class PatientRepository extends RepositoryBase implements IRepository<Patient> {
	
	/*
	 * private int patientId;
		private String name;
		private String lastName;
		private int gender;
		private int healthInsurance;
		private Date birthDate;
		private int isStudent;
	 * */
	
	public static PatientRepository Instance;
	
	public PatientRepository() throws Exception {
		super();
		if(Instance == null)
			Instance = this;
				// Debug amaciyla tabloyu siler!
				//Statement statementZero = connection.createStatement();
				//statementZero.executeUpdate("DROP TABLE IF EXISTS patients;");
				// Veritabanindaki tabloyu yaratir!
				
				Statement statement = connection.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS "
						+ "patients("
						+ "patientId INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ " name TEXT,"
						+ " lastName TEXT,"
						+ " birthDate TEXT,"
						+ " gender INTEGER,"
						+ " healthInsurance INTEGER,"
						+ " isStudent INTEGER"
						+ ");";
				statement.executeUpdate(sql);
	}
	@Override
	public void add(Patient data) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "INSERT INTO patients"
				+ "(name, lastName, birthDate, gender, healthInsurance, isStudent) VALUES ("
				+ "'" + data.getName() + "'" + ","
				+  "'" + data.getLastName() + "'" + ","
				+ "'" + data.getBirthDate().toString() + "'" + ","
				+ data.getGender() + ","
				+ data.getHealthInsurance() + ","
				+ data.getIsStudent()
				+ ");";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili, 0 - basarisiz): " + status);
	}
	@Override
	public void update(int id, Patient data) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "UPDATE patients SET "
				+ "name = " + "'" + data.getName() + "',"
				+ "lastName = " + "'" + data.getLastName() + "',"
				+ "birthDate = " + "'" + data.getBirthDate().toString() + "',"
				+ "gender = " + data.getGender() + ","
				+ "healthInsurance = " + data.getHealthInsurance() + ","
				+ "isStudent = " + data.getIsStudent()
				+ " WHERE patientId = " + id + ";";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili, 0 - basarisiz): " + status);
		
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Patient> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM patients";
				
				ResultSet rs = statement.executeQuery(sql);
				
				ArrayList<Patient> patients = new ArrayList<Patient>();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String lastName = rs.getString(3);
					String birthDate = rs.getString(4);
					int gender = rs.getInt(5);
					int healthInsurance = rs.getInt(6);
					int isStudent = rs.getInt(7);
					
					Patient patient = new Patient();
					patient.setPatientId(id);
					patient.setName(name);
					patient.setLastName(lastName);
					patient.setBirthDate(birthDate);
					patient.setGender(gender);
					patient.setHealthInsurance(healthInsurance);
					patient.setIsStudent(isStudent);
					patients.add(patient);
				}
				return patients;
	}
	@Override
	public Patient fetchById(int id) throws SQLException {
		// TODO Auto-generated method stub
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM patients WHERE patientId = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			
			Patient patient = new Patient();
			
			while(rs.next()) {
				id = rs.getInt(1);
				String name = rs.getString(2);
				String lastName = rs.getString(3);
				String birthDate = rs.getString(4);
				int gender = rs.getInt(5);
				int healthInsurance = rs.getInt(6);
				int isStudent = rs.getInt(7);
				
				patient.setPatientId(id);
				patient.setName(name);
				patient.setLastName(lastName);
				patient.setBirthDate(birthDate);
				patient.setGender(gender);
				patient.setHealthInsurance(healthInsurance);
				patient.setIsStudent(isStudent);
			}
			return patient;
	}
	public Patient fetchByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM patients WHERE patientId = " + name;
		
		ResultSet rs = statement.executeQuery(sql);
		
		Patient patient = new Patient();
		
		while(rs.next()) {
			int id = rs.getInt(1);
			name = rs.getString(2);
			String lastName = rs.getString(3);
			String birthDate = rs.getString(4);
			int gender = rs.getInt(5);
			int healthInsurance = rs.getInt(6);
			int isStudent = rs.getInt(7);
			
			patient.setPatientId(id);
			patient.setName(name);
			patient.setLastName(lastName);
			patient.setBirthDate(birthDate);
			patient.setGender(gender);
			patient.setHealthInsurance(healthInsurance);
			patient.setIsStudent(isStudent);
		}
		return patient;
	}
}
