package com.muaynetakip;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import com.muaynetakip.Models.User;
import com.muaynetakip.Models.Yetki;
import com.muaynetakip.Repository.Concrete.UserRepository;

public class PersonelKayit {
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public PersonelKayit() throws Exception {
		while(true) {
			if(Main.user.getYetki() == Yetki.HASTA) {
				out.println("Bu islem icin gerekli yetkiye sahip degilsiniz!");
				break;
			}
			out.println("PERSONEL KAYIT");
			out.println("##############");
			out.println("1. Personel Listesi");
			if(Main.user.getYetki() == Yetki.YONETICI) {
				out.println("2. Personel Ekleme");
				out.println("3. Personel Guncelleme");
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
		return false;
	}
	
private static void eklemeIslemleri() throws Exception {
		
		out.println("############################");
		out.println("#      Personel Ekleme     #");
		out.println("############################");
		
		if(Main.user.getYetki() == Yetki.YONETICI) {
			
			// Doktor user'i eklenir.
			User personnelUser = new User();
			
			out.println("Kayit Personeli hesabi icin gecerli bir kullanici adi giriniz: ");
			personnelUser.setUsername(GirdiIslemleri.girdi());
			
			out.println("Kayit Personeli hesabi icin gecerli bir sifre giriniz: ");
			personnelUser.setPassword(GirdiIslemleri.girdi());
			
			
			personnelUser.setYetki(Yetki.KAYIT_PERSONELI);
			
			
			UserRepository.Instance.add(personnelUser);
			
			out.println("################################");
			out.println("# Kullanici basariyla eklendi! #");
			out.println("################################");
			out.println("Kullanici Adi: " + personnelUser.getUsername());
			out.println("Sifre: " + personnelUser.getPassword());
			out.println("Yetki:" + personnelUser.getYetki());
			out.println("############################");
			
			//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		}
		else {
			out.println("Bu islem icin gerekli yetkiye sahip degilsiniz. Geri donuyorsunuz...");
		}
	}
	
	private static void guncellemeIslemleri() throws Exception {
		if(Main.user.getYetki() == Yetki.YONETICI) {
			int userId = -1;
			User personnelToEdit = new User();
			
			out.println("##################################");
			out.println("#   Kayit Personeli Guncelleme   #");
			out.println("##################################");
			
			out.println("Guncellenecek Personelin Numarasi: ");
			userId = Integer.valueOf(GirdiIslemleri.girdi());
			
			out.println("Kayit Personeli hesabi icin gecerli bir kullanici adi giriniz: ");
			personnelToEdit.setUsername(GirdiIslemleri.girdi());
			
			out.println("Kayit Personeli hesabi icin gecerli bir sifre giriniz: ");
			personnelToEdit.setPassword(GirdiIslemleri.girdi());

			UserRepository.Instance.update(userId, personnelToEdit);
		}
		else {
			out.println("Bu islem icin yetkiniz bulunmamaktadir. Geri donduruluyorsunuz... ");
		}
		
	}
	
	private static void listelemeIslemleri() throws Exception {
		
		if(Main.user.getYetki() == Yetki.YONETICI) {
			out.println("###################################");
			out.println("#     KAYIT PERSONELI LISTESI     #");
			out.println("###################################");
			for(User user: UserRepository.Instance.fetchAll()) {
				if(user.getYetki() == Yetki.KAYIT_PERSONELI) {
					out.println("Kullanici Adi: " + user.getUsername());
					out.println("Sifre: " + user.getPassword());
					out.println("Yetki:" + user.getYetki());
					out.println("###################################");
				}
			}
		}
		out.println("# - LISTENIN SONU.");
		
	}
}
