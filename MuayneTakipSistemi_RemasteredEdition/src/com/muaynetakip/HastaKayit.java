package com.muaynetakip;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.muaynetakip.Models.Appointment;
import com.muaynetakip.Models.Doctor;
import com.muaynetakip.Models.Patient;
import com.muaynetakip.Models.User;
import com.muaynetakip.Models.Yetki;
import com.muaynetakip.Repository.Concrete.AppointmentRepository;
import com.muaynetakip.Repository.Concrete.DoctorRepository;
import com.muaynetakip.Repository.Concrete.PatientRepository;
import com.muaynetakip.Repository.Concrete.UserRepository;

public class HastaKayit {
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public HastaKayit() throws Exception {
		
		while(true) {
			out.println("###############");
			out.println("# HASTA KAYIT #");
			out.println("###############");
			out.println("1. Muayene Talebi");
			out.println("2. Muayene Listeleme");
			
			if(Main.user.getYetki() != Yetki.HASTA) {
				out.println("3. Hasta Guncelleme");
				out.println("4. Hasta Listeleme");
				out.println("5. Menuye Don");
			}
			else {
				out.println("3. Menuye Don");
			}
			
			out.println("############");
			if(!girdiIslem()) {
				break;
			}
		}
	}
	
	private static boolean girdiIslem() throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
			
		String input = reader.readLine();
		if(Main.user.getYetki() == Yetki.YONETICI || Main.user.getYetki() == Yetki.KAYIT_PERSONELI) {
			switch(input) {
			case "1":
				muayneTalepIslemleri();
				break;
			case "2":
				muayneListelemeIslemleri();
				return true;
			case "3":
				guncellemeIslemleri();
				return false;
			case "4":
				listelemeIslemleri();
				return false;
			case "5":
				break;
			default:
				return true;
			}
		}
		else {
			switch(input) {
				case "1":
					muayneTalepIslemleri();
					return true;
				case "2":
					muayneListelemeIslemleri();
					return true;
				case "3":
					break;
				default:
					return true;
			}
		}
		return false;
	}
	
	private static void muayneListelemeIslemleri() throws Exception {
		// TODO Auto-generated method stub
		// Yonetici her seyi goruyor
		// Doktor kendi hastalarini goruyor
		// Hasta kendi randevularini goruyor
		switch(Main.user.getYetki()) {
			case YONETICI:
				out.println("#################################################################");
				out.println("# Muayene Numarasi || Doktor Numarasi || Hasta Numarasi || Muayene Tarihi #");
				out.println("#################################################################");
				if(AppointmentRepository.Instance.fetchAll().size() != 0) {
					for(Appointment a: AppointmentRepository.Instance.fetchAll()) {
						out.println("" + a.getId() + " || " + a.getDoctorId() + " || " + a.getPatientId() + " || " + a.getDate());
					}
				}
				out.println("#################################################################");
				break;
			case KAYIT_PERSONELI:
				out.println("#################################################################");
				out.println("# Muayene Numarasi || Doktor Numarasi || Hasta Numarasi || Muayene Tarihi #");
				out.println("#################################################################");
				if(AppointmentRepository.Instance.fetchAll().size() != 0) {
					for(Appointment a: AppointmentRepository.Instance.fetchAll()) {
						out.println("" + a.getId() + " || " + a.getDoctorId() + " || " + a.getPatientId() + " || " + a.getDate());
					}
				}
				out.println("#################################################################");
				break;
			case DOKTOR:
				out.println("Lutfen doktor numaranizi giriniz:");
				int doctorNumber = Integer.parseInt(GirdiIslemleri.girdi());
				if(DoctorRepository.Instance.fetchById(doctorNumber) != null) {
					out.println("#################################################################");
					out.println("# Muayene Numarasi || Hasta Numarasi || Muayene Tarihi #");
					out.println("#################################################################");
					for(Appointment a: AppointmentRepository.Instance.fetchAllByDoctorId(doctorNumber)) {
						out.println("" + a.getId() + " || " + a.getPatientId() + " || " + a.getDate());
					}
					out.println("#################################################################");
				}
				else {
					out.println("Boyle bir doktor bulunmamaktadir.");
					return;
				}
				break;
			case HASTA:
				out.println("Lutfen hasta numarasini giriniz:");
				int patientNumber = UserRepository.Instance
						.fetchById(Integer.valueOf(GirdiIslemleri.girdi())).getId();
				out.println("#################################################################");
				out.println("# Muayene Numarasi || Doktor Numarasi || Muayene Tarihi #");
				out.println("#################################################################");
				for(Appointment a: AppointmentRepository.Instance.fetchAllByPatientId(patientNumber)) {
					out.println("" + a.getId() + " || " + a.getDoctorId() + " || " + a.getDate());
				}
				out.println("#################################################################");
				break;
		}
	}
	
	private static void listelemeIslemleri() throws Exception {
		// TODO Auto-generated method stub
		// Veritabanindaki tum hastalari listeler.
		out.println("############################################################################################");
		out.println("# Hasta Numarasi || Ad || Soyad || Cinsiyet || Dogum Tarihi || Ogrenci || Saglik Sigortasi #");
		out.println("############################################################################################");
		for(Patient p: PatientRepository.Instance.fetchAll()) {
			out.println("############################################################################################");
			out.println("#" + p.getPatientId() + " : " + p.getName() + " || " + p.getLastName() + " || " + p.getGender()
			+ " || " + p.getBirthDate() + " || " + p.getIsStudent() + " || " + p.getHealthInsurance());
		}
		
	}
	
	private static void guncellemeIslemleri() throws Exception {
		// TODO Auto-generated method stub
		Patient patientToUpdate = new Patient();
		
		out.println("Guncellenecek Hasta Numarasini giriniz: ");
		final int hastaNumber = Integer.parseInt(GirdiIslemleri.girdi());
		Patient patientInDb = PatientRepository.Instance.fetchById(hastaNumber);
		if(patientInDb != null) {
			out.println("Hasta Adi: ");
			patientToUpdate.setName(GirdiIslemleri.girdi());
			
			out.println("Hasta Soyadi: ");
			patientToUpdate.setLastName(GirdiIslemleri.girdi());
			
			out.println("Hasta Cinsiyeti (1: Erkek, diger degerler: Kadin) : ");
			patientToUpdate.setGender(Integer.valueOf(GirdiIslemleri.girdi()));
			
			out.println("Hasta Dogum Tarihi (ex. 31/07/1997): ");
			patientToUpdate.setBirthDate(format.parse(GirdiIslemleri.girdi()).toString());
			
			out.println("Hasta ogrenci mi? (1: Evet / Diger: Hayir): ");
			patientToUpdate.setIsStudent(Integer.parseInt(GirdiIslemleri.girdi()));
			
			out.println("Sigorta turunuzu giriniz (0-Yok 1-SGK 2-Yesil Kart 3-GSS 4-Özel Sigorta):");
			int insuranceType = Integer.parseInt(GirdiIslemleri.girdi());
			
			patientToUpdate.setHealthInsurance(insuranceType);
			
			PatientRepository.Instance.update(patientInDb.getPatientId(), patientToUpdate);
			out.println("Hasta bilgileri basariyla guncellendi.");
		}
		else {
			out.println("Boyle bir hasta mevcut degildir. Lutfen tekrar deneyiniz.");
		}
		
		
	}
	
	private static void muayneTalepIslemleri() throws NumberFormatException, IOException, ParseException, Exception {
		// TODO Auto-generated method stub
		
		int insuranceType = 0; // Hastanin sigorta tipi
		int appointmentType = -1; // Hastanin talep ettigi randevu tipi
		
		Patient patient = new Patient();
		
		out.println("Hasta Adi: ");
		patient.setName(GirdiIslemleri.girdi());
		
		out.println("Hasta Soyadi: ");
		patient.setLastName(GirdiIslemleri.girdi());
		
		out.println("Hasta Cinsiyeti (1: Erkek, diger degerler: Kadin) : ");
		patient.setGender(Integer.valueOf(GirdiIslemleri.girdi()));
		
		out.println("Hasta Dogum Tarihi (ex. 31/07/1997): ");
		patient.setBirthDate(format.parse(GirdiIslemleri.girdi()).toString());
		
		out.println("Hasta ogrenci mi? (1: Evet / Diger: Hayir): ");
		patient.setIsStudent(Integer.parseInt(GirdiIslemleri.girdi()));
		
		out.println("Sigorta turunuzu giriniz (0-Yok 1-SGK 2-Yesil Kart 3-GSS 4-Özel Sigorta):");
		insuranceType = Integer.parseInt(GirdiIslemleri.girdi());
		out.println("Muayene turunu giriniz (KBB-1, Göz-2, Genel Cerrahi-3):");
		appointmentType = Integer.parseInt(GirdiIslemleri.girdi());
		
		out.println("Sigorta turunuz: " + insuranceType);
		out.println("Muayene turunuz: " + appointmentType);
		
		DoctorRepository.Instance.fetchByDept(appointmentType);
		
		out.println("Randevu alinabilecek doktorlarin listesi:");
		out.println("#####################################");
		out.println("# Numara || Ad || Soyad || Cinsiyet #");
		out.println("#####################################");
		
		for(Doctor d: DoctorRepository.Instance.fetchAllByDept(appointmentType)) {
			out.println("" + d.getId() + " || " + d.getName() + " || " + d.getLastName() + " || " + d.getTrueGender());
		}
		out.println("#####################################\n");
		if(DoctorRepository.Instance.fetchAllByDept(appointmentType).size() == 0) {
			out.println("Malesef hicbir doktor bu muayene tipini yapmamaktadir. Geri donuyorsunuz...\n");
			return;
		}
		else {
			out.println("Randevusunu almak istediginiz doktorun numarasini giriniz: ");
			final int doctorNumber = Integer.parseInt(GirdiIslemleri.girdi());
			
			Doctor doctor = (Doctor) DoctorRepository.Instance.fetchByDept(appointmentType);
			
			Date date = null;adiniz
			
			if(doctor != null) {
				// Hasta ve Randevu veritabanina eklenir.
				PatientRepository.Instance.add(patient);
				
				boolean awaitingDate = true;
				
				while(awaitingDate) {
					out.println("Randevu icin gecerli bir tarih giriniz: ");
					try {
						date = format.parse(GirdiIslemleri.girdi());
						awaitingDate = false;
					} catch(Exception ex) {
						out.println("Lutfen gecerli bir tarih giriniz!");
					}
				}
				
				// Doktor user'i eklenir.
				User patientUser = new User();
				
				out.println("Hasta hesabi icin gecerli bir kullanici adi giriniz: ");
				patientUser.setUsername(GirdiIslemleri.girdi());
				
				out.println("Hasta hesabi icin gecerli bir sifre giriniz: ");
				patientUser.setPassword(GirdiIslemleri.girdi());
				
				patientUser.setYetki(Yetki.HASTA);
				
				UserRepository.Instance.add(patientUser);
				
				Appointment appointment = new Appointment();
				appointment.setAppointmentType(appointmentType);
				appointment.setDoctorId(doctor.getId());
				appointment.setPatientId(UserRepository.count());
				appointment.setDate(date.toString());
				
				// BURADA RANDEVUNUN UCRETI HESAPLANMALIDIR.
				
				double price = 300.0;
				
				//
				appointment.setPrice(price);
				
				AppointmentRepository.Instance.add(appointment);
				
				out.println("Randevunuz sistemimize basariyla eklenmistir!\n"
						+ "Hasta numaraniz " + UserRepository.Instance.count() + ". "
						+ "Randevunuza listeleme islemlerinden bakabilirsiniz.\n"
						+ appointment.getDate() + " tarihinde sizi hastanemizde bekleriz!");
			} 
			else {
				out.println("Boyle bir doktor sistemimizde bulunmamaktadir.");
			}
		}	
	}
	
}
