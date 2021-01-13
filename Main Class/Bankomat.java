import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Bankomat {

	
	
	static Scanner input = new Scanner(System.in);
	static KorisniciDAOImplementation korisnikDAOImplementationObject= new KorisniciDAOImplementation();
	
	
	public static void main(String[] args) throws EOFException, IOException, ClassNotFoundException, SQLException  {
		
	
		signUpOrLogIn();
		
		
	}

	
	public static void signUpOrLogIn() throws SQLException, EOFException, IOException{
		
		System.out.println("Unesite 1 ako se zelite prijaviti"+" unesite 2 ako se zelite uloginovati ");
		
		int option = input.nextInt();
		
		switch(option) {
		
		case 1:
			signUp();
			break;
			
		case 2:
			logIn();
			break;

		}
		
	}
	
	
	public static void logIn() throws SQLException, EOFException, IOException, NullPointerException {


		System.out.print("Enter ur username: ");
		String username = input.next();

		System.out.print("Enter ur password: ");
		String password = input.next();

		Korisnik user = korisnikDAOImplementationObject.findKorisnik(username);
	
		if(user.getPassword().equals(password)) {
			
			System.out.println("Unesite 1 kako bi se odlogovali");
			System.out.println("Unesite 2 kako bi promijenili ime");
			System.out.println("Unesite 3 kako bi promijenili prezime");
			System.out.println("Unesite 4 kako bi promijenili username");
			System.out.println("Unesite 5 kako bi promijenili password");
			System.out.println("Unesite 6 kako bi isli na bankomat");
			

			int option = input.nextInt();
			
			switch(option) {
			case 1:
				
				signUpOrLogIn();
				break;
				
			case 2:
				
				changeName(username);
				break;
				
			case 3:
				
				changeLastname(username);
				break;
				
			case 4:
				
				changeUsername(username);
				break;
				
			case 5:
				
				changePassword(username);
				break;
				
			case 6:
				
				glavniMeni();
				break;
			
			}
			
		}
		
		
		
	}
	
	
	public static void changeName(String username) throws SQLException, EOFException, NullPointerException, IOException{
		
		System.out.println("Unesite novo ime");
		
		String novoIme = input.next();
		
		korisnikDAOImplementationObject.changeName(username, novoIme);
		System.out.println("Ime je promijenjeno");
		logIn();
		
		
	}
	
	
	public static void changeLastname(String username) throws SQLException, EOFException, NullPointerException, IOException{
		
		System.out.println("Unesite novo prezime");
		
		String novoPrezime = input.next();
		
		korisnikDAOImplementationObject.changeLastname(username, novoPrezime);
		System.out.println("Prezime je promijenjeno");
		logIn();
		
	}
	
	
	public static void changeUsername(String username) throws SQLException, EOFException, NullPointerException, IOException{

		System.out.println("Unesite novi username");
		
		String noviUsername = input.next();
		
		korisnikDAOImplementationObject.changeUsername(username, noviUsername);
		System.out.println("Username je promijenjen");
		logIn();
		
	}
	
	
	public static void changePassword(String username) throws SQLException, EOFException, NullPointerException, IOException {

		System.out.println("Unesite novi password");
		
		String noviPassword = input.next();
		
		if(Validacija.returnTrueIfPasswordValid(noviPassword)) {
		
		korisnikDAOImplementationObject.changePassword(username,noviPassword );
		System.out.println("Password je promijenjen");
		
		} else {
			
			System.out.println("Ponovo se ulogujte pa pokusajte sa vecim passwordom...");
			
			
		}
		logIn();
		
	}
	
	public static void signUp() throws SQLException, EOFException, IOException{
		
		
		System.out.print("Enter ur first name: ");
		String name = input.next();

		System.out.print("Enter ur last name: ");
		String lastname = input.next();

		System.out.print("Enter ur username: ");
		String username = input.next();

		System.out.print("Enter ur password: ");
		String password = input.next();

		
		
		if(Validacija.returnTrueIfPasswordValid(password)  && Validacija.returnTrueIfUserDoesntExist(username)) {
			
			Korisnik korisnik = new Korisnik(name,lastname,username,password);
			korisnikDAOImplementationObject.addKorisnik(korisnik);
			signUpOrLogIn();
			
		} else {  
			
			System.out.println("Sifra nije kako treba ili je username vec zauzet, pokusajte ponovo...");
			signUp();
			
		}
		
		
		
		
	}
	
	public static boolean shouldReturnTrueIfUnosIsValid(int unos) {
		
			if (unos>0 && unos<5) {
				
				return true;
			
			}
			
		return false;
	}
	
	
	public static void glavniMeni() throws EOFException, IOException, SQLException {
		
		
		System.out.println("1. Kreiranje racuna");
		System.out.println("2. Prebacivanje novca");
		System.out.println("3. Ispisivanje postojeceg racuna");
		System.out.println("4. Napustite aplikaciju");
		System.out.println("Pisite zeljeni broj za odredjenu akciju");
		
		int unos=input.nextInt();

		opcija(unos);
		glavniMeni();	

	}

	public static boolean opcija(int unos) throws EOFException, IOException, SQLException {
		if(shouldReturnTrueIfUnosIsValid(unos)) {
			
			if(unos==1) {
				
				kreiranjeRacuna();
				return true;
			    
				}
		
			if (unos==2) {
				
				prebacivanjeNovca();
				return true;
				
				}
			if(unos==3) {
				
				ispisivanjeStanjaNaRacunu();
				return true;
				
				}
			
			if(unos==4) {
				
				signUpOrLogIn();
				
			}
			
			} else {
				
				System.out.println("Unos nije validan, ukucajte broj ponovo...");
				return false;
				
			}
		
		return false;
		
	}
	
	public static void kreiranjeRacuna() throws EOFException, IOException, SQLException {
		
		System.out.println("Unesite broj racuna");
		int brojRacuna = input.nextInt();
		
		System.out.println("Unesite iznos na racunu");
		int iznosNaRacunu=input.nextInt();
		String imeVlasnika2=input.nextLine();
		
		System.out.println("Unesite ime vlasnika");
		String imeVlasnika=input.nextLine();
		
		
		napravljenRacun(brojRacuna, iznosNaRacunu, imeVlasnika);
		glavniMeni();
	   
	}
	
	public static boolean napravljenRacun(int brojRacuna, int iznosNaRacunu, String imeVlasnika) throws EOFException, IOException {
		if(provjeraKreiranjaRacuna(brojRacuna, iznosNaRacunu, imeVlasnika)) {
			
			
			Racun.racuni.add(new Racun(brojRacuna, iznosNaRacunu, imeVlasnika));
			System.out.println("Racun je kreiran...");
			return true;
			
		} else {
			
			System.out.println("Racun nije kreiran...");
			return false;
		}
	    
	    
	}
	
	public static void prebacivanjeNovca() throws EOFException, IOException, SQLException {
		
		System.out.println("Unesite racun s kojeg hocete da prebacite novac");
		int sourceBrRacuna=input.nextInt();
		System.out.println("Unesite racun na koji hocete da prebacite novac");
		int targetBrRacuna=input.nextInt();
		System.out.println("Unesite iznos koji hocete da prebacite");
		int iznos=input.nextInt();
		
		prijeProvjereTransfera( sourceBrRacuna, targetBrRacuna, iznos);
		glavniMeni();
		
	}
	
	public static boolean prijeProvjereTransfera(int sourceBrRacuna,int targetBrRacuna,int iznos) {
		
		if (provjeraTransfera(sourceBrRacuna,targetBrRacuna,iznos)) {
			
			transferNovca(sourceBrRacuna, targetBrRacuna, iznos);
			return true;
		}
		return false;
	}
	
	public static void ispisivanjeStanjaNaRacunu() throws EOFException, IOException, FileNotFoundException, SQLException {
		
		System.out.println("Unesite vas broj racuna");
		int brojRacuna=input.nextInt();
		
		ispisi(brojRacuna);
		glavniMeni();
		
	}
	

	public static boolean ispisi(int brojRacuna) throws EOFException, IOException {
		
		if(Racun.getRacun(brojRacuna) != null) {
			
			Racun.ispisiRacun(brojRacuna);
			return true;
		}
		
		return false;
		
	}
		
	
	public static void transferNovca(int sourceBrRacuna, int targetBrRacuna, int iznos) {
		
		if(provjeraTransfera(sourceBrRacuna, targetBrRacuna, iznos)) {
			
			Racun.getRacun(sourceBrRacuna).setIznosNaRacunu(Racun.getRacun(sourceBrRacuna).getIznosNaRacunu()-iznos);
			Racun.getRacun(targetBrRacuna).setIznosNaRacunu(Racun.getRacun(sourceBrRacuna).getIznosNaRacunu()+iznos);
			System.out.println("Transfer je uspjesan");
		
		}
			
		
	}
	
	
	public static boolean provjeraTransfera(int sourceBrRacuna, int targetBrRacuna, int iznos) {
		
		boolean passedAllTests = true;
		boolean postojiSourceRacun = true;
		boolean postojiTargetRacun = true;
		
				
		if(Racun.getRacun(sourceBrRacuna) == null) {
			
			System.out.println("Source broj racuna ne postoji!");
			passedAllTests=false;
			postojiSourceRacun=false;
		}
		
		if(Racun.getRacun(targetBrRacuna) == null) {
			
			System.out.println("Target broj racuna ne postoji!");
			passedAllTests=false;
			postojiTargetRacun=false;
		}
		
		if(postojiSourceRacun  && postojiTargetRacun) {
			
			if(Racun.getRacun(sourceBrRacuna).getIznosNaRacunu()-iznos<0) {
			System.out.println("Iznos na source racunu nije dovoljan za transfer");
			passedAllTests = false;
			
			}
		}
		if(passedAllTests) {
			return true;
		}
		
		return false;
		
	}
	
	
	public static boolean provjeraKreiranjaRacuna(int brojRacuna,double iznosNaRacunu2,String imeVlasnika) throws EOFException, IOException  {
		
		if(brojRacuna<0) {
			System.out.println("Ne mozete unijeti negativan broj");
			return false;
		}
		
		
		for(int i=0;i<Racun.racuni.size();i++) {
			if(Racun.racuni.get(i).getBrojRacuna()== brojRacuna) {
				System.out.println("Postojeci racun vec postoji");
				return false;
			}
		}
		
		if(iznosNaRacunu2<0) {
			System.out.println("Imate negativan iznos na racunu, ne mozete napraviti racun");
			return false;
		}
		
		
		return true;
		
		}
	
	
	
}
