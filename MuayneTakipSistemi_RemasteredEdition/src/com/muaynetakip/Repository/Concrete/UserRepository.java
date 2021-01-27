package com.muaynetakip.Repository.Concrete;

import static java.lang.System.out;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.muaynetakip.Models.User;
import com.muaynetakip.Models.Yetki;
import com.muaynetakip.Repository.Abstract.IRepository;
import com.muaynetakip.Repository.Abstract.RepositoryBase;

public class UserRepository extends RepositoryBase implements IRepository<User> {
	
	/*
	 * 
	 * 	private int id;
		private String username;
		private String password;
		private Yetki yetki;
	 * 
	 * */
	public static UserRepository Instance;
	
	public UserRepository() throws SQLException {
		super();
		
		if(Instance == null) 
			Instance = this;
		
		// Debug amaciyla tabloyu siler!
		//Statement statementZero = connection.createStatement();
		//statementZero.executeUpdate("DROP TABLE IF EXISTS users;");
		// Veritabanindaki tabloyu yaratir!
		
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS "
				+ "users("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " username TEXT,"
				+ " password TEXT,"
				+ " yetki TEXT"
				+ ");";
		out.println(sql);
		statement.executeUpdate(sql);
		
		// Creates YONETICI.
		if(UserRepository.Instance.fetchAll().size() == 0) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("1234");
			user.setYetki(Yetki.YONETICI);
			
			UserRepository.Instance.add(user);
		}
	}
	public static void main(String[] args) {
		
		try {
			UserRepository ur = new UserRepository();
			out.println(count());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static int count() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT COUNT(*) FROM users";
		
		ResultSet rs = statement.executeQuery(sql);
		return rs.getInt(1);
	}

	@Override
	public void add(User data) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "INSERT INTO users"
				+ "(username, password, yetki) VALUES ("
				+ "'" + data.getUsername() + "'" + ","
				+  "'" + data.getPassword() + "'" + ","
				+ "'" + data.getYetki() + "'"
				+ ");";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili): " + status);
		
	}

	@Override
	public void update(int id, User data) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Statement statement = connection.createStatement();
		String sql = "UPDATE users SET "
				+ "username = " + "'" + data.getUsername() + "',"
				+ "password = " + "'" + data.getPassword() + "',"
				+ "yetki = " + "'" + data.getYetki().toString() + "'"
				+ " WHERE id = " + id + ";";
		out.println(sql);
		int status = statement.executeUpdate(sql);
		out.println("Sorgu durumu (1 - basarili): " + status);
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM users";
		
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		while(rs.next()) {
			User user = new User();
			
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			String yetki = rs.getString(4);
			
			user.setId(id);
			user.setUsername(username);
			user.setPassword(password);
			user.setYetki(Yetki.valueOf(yetki));
			users.add(user);
		}
		return users;
	}

	@Override
	public User fetchById(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM users WHERE id = " + id;
				
				ResultSet rs = statement.executeQuery(sql);
				
				User user = new User();
				
				while(rs.next()) {
					
					id = rs.getInt(1);
					String username = rs.getString(2);
					String password = rs.getString(3);
					String yetki = rs.getString(4);
					
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setYetki(Yetki.valueOf(yetki));
				}
				return user;
	}
	
	public User fetchByUsername(String username) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM users WHERE username = " + username;
				
				ResultSet rs = statement.executeQuery(sql);
				
				User user = new User();
				
				while(rs.next()) {
					
					int id = rs.getInt(1);
					username = rs.getString(2);
					String password = rs.getString(3);
					String yetki = rs.getString(4);
					
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setYetki(Yetki.valueOf(yetki));
				}
				return user;
	}

}
