package com.muaynetakip;
import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.muaynetakip.Models.User;
import com.muaynetakip.Models.Yetki;
import com.muaynetakip.Repository.Concrete.AppointmentRepository;
import com.muaynetakip.Repository.Concrete.DoctorRepository;
import com.muaynetakip.Repository.Concrete.PatientRepository;
import com.muaynetakip.Repository.Concrete.UserRepository;

public class Main {
	
	public static User user;
	
	public static void main(String[] args) throws Exception {
		
		// Tum data depolari yaratilir.
		
		DoctorRepository dr = new DoctorRepository();
		PatientRepository pr = new PatientRepository();
		AppointmentRepository ar = new AppointmentRepository();
		UserRepository ur = new UserRepository();
		
		boolean foundUser = false;
		
		while(!foundUser) {
			
			out.println("Muayne Takip Sistemi - Kullanici Girisi");
			user = new User();
			out.println("Kullanici adi: ");
			user.setUsername(GirdiIslemleri.girdi());
			out.println("Kullanici sifresi: ");
			user.setPassword(GirdiIslemleri.girdi());
			for(User u: UserRepository.Instance.fetchAll()) {
				if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
					user = u;
					foundUser = true;
					break;
				}
			}
			if(!foundUser)
				out.println("Boyle bir kullanici bulunamadi.");
		}
		
		while(true) {
			out.println("Muayne Takip Sistemine hosgeldiniz! Lutfen yapmak istediginiz islemi asagiya giriniz:");
			out.println("1: Personel Kayit");
			out.println("2: Doktor Kayit");
			out.println("3: Hasta Kayit");
			out.println("4: Cikis Kayit\n");
			out.println("(Yetki Seviyeniz: " + user.getYetki() + ")");
			if(!processMenuOptions())
				break;
		}
	}
	private static boolean processMenuOptions() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		
		String input = reader.readLine();
		
		switch(input) {
			case "1":
				new PersonelKayit();
				return true;
			case "2":
				new DoktorKayit();
				return true;
			case "3":
				new HastaKayit();
				return true;
			case "4":
				break;
			default:
				out.println("Bilinmeyen komut. Lutfen tekrar deneyiniz. \n");
				return true;
		}
		return false;
	}
	
}
