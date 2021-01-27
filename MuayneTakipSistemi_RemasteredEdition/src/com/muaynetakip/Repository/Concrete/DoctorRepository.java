package com.muaynetakip.Repository.Concrete;
import static java.lang.System.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Repository.Abstract.IRepository;
import com.muaynetakip.Repository.Abstract.RepositoryBase;

/*
 * 	private int id;
	private String name;
	private String lastName;
	private Date birthDate;
	private int gender;
	private int grade;
 * 
 * */

public class DoctorRepository extends RepositoryBase implements IRepository<Doctor> {
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	public static DoctorRepository Instance;
	
	public DoctorRepository() throws SQLException {
		super();
		if(Instance == null) 
			Instance = this;
		
		// Debug amaciyla tabloyu siler!
		//Statement statementZero = connection.createStatement();
		//statementZero.executeUpdate("DROP TABLE IF EXISTS doctors;");
		// Veritabanindaki tabloyu yaratir!
		
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ "doctors("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " name TEXT,"
				+ " lastName TEXT,"
				+ " birthDate TEXT,"
				+ " gender INTEGER,"
				+ " department INTEGER,"
				+ " grade INTEGER"
				+ ");";
		statement.executeUpdate(sql);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public ArrayList<Doctor> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM doctors";
		
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		
		while(rs.next()) {
			Doctor doctor = new Doctor();
			
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String lastName = rs.getString(3);
			String birthDate = rs.getString(4);
			int gender = rs.getInt(5);
			int department = rs.getInt(6);
			int grade = rs.getInt(7);
			
			doctor.setId(id);
			doctor.setName(name);
			doctor.setLastName(lastName);
			doctor.setBirthDate(birthDate);
			doctor.setGender(gender);
			doctor.setDepartment(department);
			doctor.setGrade(grade);
			doctors.add(doctor);
		}
		return doctors;
	}

	@Override
	public Doctor fetchById(int id) throws Exception {
		// TODO fetch doctors by KBB vs.
				Doctor doctor = new Doctor();
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM doctors WHERE id = " + id + ";";
				
				ResultSet rs = statement.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt(1);
					String name = rs.getString(2);
					String lastName = rs.getString(3);
					String birthDate = rs.getString(4);
					int gender = rs.getInt(5);
					int department = rs.getInt(6);
					int grade = rs.getInt(7);
					
					doctor.setId(id);
					doctor.setName(name);
					doctor.setLastName(lastName);
					doctor.setBirthDate(birthDate);
					doctor.setGender(gender);
					doctor.setDepartment(department);
					doctor.setGrade(grade);
				}
				out.println(rs);
				return doctor;
	}

	@Override
	public void add(Doctor data) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "INSERT INTO doctors"
				+ "(name, lastName, birthDate, gender, department, grade) VALUES ("
				+ "'" + data.getName() + "'" + ","
				+  "'" + data.getLastName() + "'" + ","
				+ "'" + data.getBirthDate().toString() + "'" + ","
				+ data.getGender() + ","
				+ data.getDepartment() + ","
				+ data.getGrade()
				+ ");";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili): " + status);
	}

	@Override
	public void update(int id, Doctor data) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "UPDATE doctors SET "
				+ "name = " + "'" + data.getName() + "',"
				+ "lastName = " + "'" + data.getLastName() + "',"
				+ "birthDate = " + "'" + data.getBirthDate().toString() + "',"
				+ "gender = " + data.getGender() + ","
				+ "department = " + data.getDepartment() + ","
				+ "grade = " + data.getGrade()
				+ " WHERE id = " + id + ";";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili): " + status);
		
	}
	public Doctor fetchByDept(int deptId) throws SQLException, ParseException {
		// TODO fetch doctors by KBB vs.
		Doctor doctor = new Doctor();
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM doctors WHERE department = " + deptId + ";";
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String lastName = rs.getString(3);
			String birthDate = rs.getString(4);
			int gender = rs.getInt(5);
			int department = rs.getInt(6);
			int grade = rs.getInt(7);
			doctor.setId(id);
			doctor.setName(name);
			doctor.setLastName(lastName);
			doctor.setBirthDate(birthDate);
			doctor.setGender(gender);
			doctor.setDepartment(department);
			doctor.setGrade(grade);
		}
		out.println(rs);
		return doctor;
	}
	public ArrayList<Doctor> fetchAllByDept(int deptId) throws SQLException {
		// TODO fetch doctors by KBB vs.
				ArrayList<Doctor> doctors = new ArrayList<Doctor>();
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM doctors WHERE department = " + deptId + ";";
				
				ResultSet rs = statement.executeQuery(sql);
				
				while(rs.next()) {
					Doctor doctor = new Doctor();
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String lastName = rs.getString(3);
					String birthDate = rs.getString(4);
					int gender = rs.getInt(5);
					int department = rs.getInt(6);
					int grade = rs.getInt(7);
					
					doctor.setId(id);
					doctor.setName(name);
					doctor.setLastName(lastName);
					doctor.setBirthDate(birthDate);
					doctor.setGender(gender);
					doctor.setDepartment(department);
					doctor.setGrade(grade);
					doctors.add(doctor);
				}
				return doctors;
	}
}
