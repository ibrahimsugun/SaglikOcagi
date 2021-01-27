package com.muaynetakip;

import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Models.Patient;
import com.muaynetakip.Models.User;
import com.muaynetakip.Repository.Concrete.DoctorRepository;
import com.muaynetakip.Repository.Concrete.PatientRepository;
import com.muaynetakip.Repository.Concrete.UserRepository;

import static java.lang.System.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Test {
	public static void main(String[] args) throws Exception {
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DoctorRepository dr = new DoctorRepository();
		PatientRepository pr = new PatientRepository();
		Doctor d = new Doctor();
		d.setName("eray");
		d.setLastName("onur");
		d.setGender(1);
		d.setDepartment(1);
		d.setBirthDate(format.parse("31/07/1997").toString());
		DoctorRepository.Instance.add(d);
		d.setGender(2);
		DoctorRepository.Instance.update(1, d);
		
		UserRepository ur = new UserRepository();
		User user = new User();
		user.setUsername("eray_onur");
		user.setPassword("123456");
		UserRepository.Instance.add(user);
		out.println(UserRepository.Instance.fetchAll());
	}
}
