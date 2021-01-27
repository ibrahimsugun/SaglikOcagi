package com.muaynetakip;
import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Models.User;
import com.muaynetakip.Models.Yetki;
import com.muaynetakip.Repository.Concrete.DoctorRepository;
import com.muaynetakip.Repository.Concrete.UserRepository;

public class DoktorKayit {
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public DoktorKayit() throws Exception {
		while(true) {
			if(Main.user.getYetki() == Yetki.HASTA) {
				out.println("Bu islem icin gerekli yetkiye sahip degilsiniz!");
				break;
			}
			out.println("DOKTOR KAYIT");
			out.println("############");
			out.println("1. Doktor Listesi");
			if(Main.user.getYetki() == Yetki.YONETICI) {
				out.println("2. Doktor Ekleme");
				out.println("3. Doktor Guncelleme");
				out.println("4. Menuye Don");
			}
			else {
				out.println("2. Menuye Don");
			}
			out.println("############");
			if(!girdiIslem()) {
				break;
			}
		}
	}
	
	private static boolean girdiIslem() throws Exception {
		
		BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in)); 
		
		String input = reader.readLine();
		if(Main.user.getYetki() == Yetki.YONETICI) {
			switch(input) {
			case "1":
				listelemeIslemleri();
				return true;
			case "2":
				eklemeIslemleri();
				return true;
			case "3":
				guncellemeIslemleri();
				break;
			case "4":
				return false;
			default:
				return true;
			}
		}
		else {
			switch(input) {
			case "1":
				listelemeIslemleri();
				return true;
			case "2":
				return false;
			default:
				return true;
			}
		}
		
		return false;
	}
	
	
	
	private static void eklemeIslemleri() throws Exception {
		
		out.println("############################");
		out.println("#       Doktor Ekleme      #");
		out.println("############################");
		
		if(Main.user.getYetki() == Yetki.YONETICI) {
			Doctor doctor = new Doctor();
			
			out.println("Doktor Adi: ");
			doctor.setName(GirdiIslemleri.girdi());
			
			out.println("Doktor Soyadi: ");
			doctor.setLastName(GirdiIslemleri.girdi());
			
			out.println("Doktor Cinsiyeti (1: Erkek, Diger degerler: Kadin) : ");
			doctor.setGender(Integer.valueOf(GirdiIslemleri.girdi()));
			
			out.println("Doktor Dogum Tarihi (ex. 31/07/1997): ");
			doctor.setBirthDate(format.parse(GirdiIslemleri.girdi()).toString());
			
			out.println("Doktor Diploma Notu: ");
			doctor.setGrade(Integer.valueOf(GirdiIslemleri.girdi()));
			
			out.println("Doktorun departmani (KBB-1, Göz-2, Genel Cerrahi-3):");
			doctor.setDepartment(Integer.valueOf(GirdiIslemleri.girdi()));
			
			DoctorRepository.Instance.add(doctor);
			
			out.println("############################");
			out.println("# Kayit basariyla eklendi! #");
			out.println("############################");
			out.println("Ad Soyad: " + doctor.getName() + " " + doctor.getLastName());
			out.println("Cinsiyet: " + doctor.getTrueGender());
			out.println("Dogum Tarihi:" + doctor.getBirthDate());
			out.println("Diploma Notu:" + doctor.getGrade());
			out.println("Bolumu: " + doctor.getDepartment());
			out.println("############################");
			
			// Doktor user'i eklenir.
			User doctorUser = new User();
			
			out.println("Doktor hesabi icin gecerli bir kullanici adi giriniz: ");
			doctorUser.setUsername(GirdiIslemleri.girdi());
			
			out.println("Doktor hesabi icin gecerli bir sifre giriniz: ");
			doctorUser.setPassword(GirdiIslemleri.girdi());
			
			
			doctorUser.setYetki(Yetki.DOKTOR);
			
			
			UserRepository.Instance.add(doctorUser);
			
			out.println("################################");
			out.println("# Kullanici basariyla eklendi! #");
			out.println("################################");
			out.println("Kullanici Adi: " + doctorUser.getUsername());
			out.println("Sifre: " + doctorUser.getPassword());
			out.println("Yetki:" + doctorUser.getYetki());
			out.println("############################");
			
			//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		}
		else {
			out.println("Bu islem icin gerekli yetkiye sahip degilsiniz. Geri donuyorsunuz...");
		}
	}
	
	private static void guncellemeIslemleri() throws Exception {
		if(Main.user.getYetki() == Yetki.YONETICI) {
			int doctorId = -1;
			Doctor doctorToEdit = new Doctor();
			
			out.println("############################");
			out.println("#     Doktor Guncelleme    #");
			out.println("############################");
			
			out.println("Guncellenecek Doktorun Numarasi: ");
			doctorId = Integer.valueOf(GirdiIslemleri.girdi());
			
			out.println("Doktor Adi: ");
			doctorToEdit.setName(GirdiIslemleri.girdi());
			
			out.println("Doktor Soyadi: ");
			doctorToEdit.setLastName(GirdiIslemleri.girdi());
			
			out.println("Doktor Cinsiyeti: ");
			doctorToEdit.setGender(Integer.valueOf(GirdiIslemleri.girdi()));
			
			out.println("Doktor Dogum Tarihi: ");
			doctorToEdit.setBirthDate(format.parse(GirdiIslemleri.girdi()).toString());
			
			out.println("Doktor Diploma Notu: ");
			doctorToEdit.setGrade(Integer.valueOf(GirdiIslemleri.girdi()));
			
			out.println("Doktorun departmani (KBB-1, Göz-2, Genel Cerrahi-3):");
			doctorToEdit.setDepartment(Integer.valueOf(GirdiIslemleri.girdi()));
			
			DoctorRepository.Instance.update(doctorId, doctorToEdit);
		}
		else {
			out.println("Bu islem icin yetkiniz bulunmamaktadir. Geri donduruluyorsunuz... ");
		}
		
	}
	
	private static void listelemeIslemleri() throws Exception {
		
		out.println("############################");
		out.println("#       DOKTOR LISTESI     #");
		out.println("############################");
		for(Doctor d: DoctorRepository.Instance.fetchAll()) {
			out.println("Ad Soyad: " + d.getName() + " " + d.getLastName());
			out.println("Cinsiyet: " + d.getTrueGender());
			out.println("Dogum Tarihi:" + d.getBirthDate());
			out.println("Diploma Notu:" + d.getGrade());
			out.println("############################");
		}
		if(Main.user.getYetki() == Yetki.YONETICI) {
			out.println("######################################");
			out.println("#       DOKTOR KULLANICI LISTESI     #");
			out.println("######################################");
			for(User user: UserRepository.Instance.fetchAll()) {
				if(user.getYetki() == Yetki.DOKTOR) {
					out.println("Kullanici Adi: " + user.getUsername());
					out.println("Sifre: " + user.getPassword());
					out.println("Yetki:" + user.getYetki());
					out.println("######################################ad");
				}
			}
		}
		out.println("# - LISTENIN SONU.");
		
	}
	
}
