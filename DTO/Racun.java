import java.util.ArrayList;

public class Racun {

	private int brojRacuna;
	private String imeVlasnika;
	private double iznosNaRacunu;
	
	static ArrayList<Racun> racuni = new ArrayList<Racun>();
	
	Racun(int brojRacuna, double iznosNaRacunu, String imeVlasnika){

				this.brojRacuna=brojRacuna;
				this.imeVlasnika=imeVlasnika;
				this.iznosNaRacunu=iznosNaRacunu;
				
	}
		
	

	public int getBrojRacuna() {
		return brojRacuna;
	}



	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}



	public String getImeVlasnika() {
		return imeVlasnika;
	}



	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}



	public double getIznosNaRacunu() {
		return iznosNaRacunu;
	}



	public void setIznosNaRacunu(double iznosNaRacunu) {
		this.iznosNaRacunu = iznosNaRacunu;
	}



	public  static void ispisiRacun(int brojRacuna) {
		
		Racun racunKojiSeTrazi = null;
		
		for(int i=0;i<racuni.size();i++) {
			
			if (racuni.get(i).getBrojRacuna() == brojRacuna) {
			racunKojiSeTrazi = racuni.get(i);
			System.out.println("Broj racuna je: "+racuni.get(i).getBrojRacuna()+" Ime vlasnika: "+racuni.get(i).getImeVlasnika()+" Stanje na racunu "+racuni.get(i).getIznosNaRacunu());
		
		}
		
		
			
	} 
}
	
	public static Racun getRacun(int brojRacuna) {
		
		for(int i=0;i<racuni.size();i++) {
			if (racuni.get(i).brojRacuna==brojRacuna) 
			return racuni.get(i);
		}
		
		return null;
		
	}
	

	
	
	
}
