import org.junit.Assert;
import org.junit.Test;

public class RacunTest {

	@Test
	public void shouldReturnAccountWhenGivenAccountNumber() {
		
		Racun accountForTesting = new Racun (34, 17, "k");
		Racun.racuni.add(accountForTesting);
		Assert.assertEquals(accountForTesting, Racun.getRacun(34));
	}
	
	@Test
	public void shouldReturnNullIfThereIsNoSuchAccount() {
		
		Assert.assertNull(Racun.getRacun(80));
		
	}
	
	@Test 
	public void shouldSetAccountNumber() {
		
		Racun racun = new Racun(2,2,"");
		Racun.racuni.add(racun);
		racun.setBrojRacuna(3);
		Assert.assertEquals(racun.getBrojRacuna(), 3);
		
	}
	
	@Test 
	public void shouldSetAccountName() {
		
		Racun racun = new Racun(2,2,"");
		Racun.racuni.add(racun);
		racun.setImeVlasnika("vlasnik");
		racun.getImeVlasnika().equals("vlasnik");
		
	}
	
	@Test
	public void shouldSetAccountMoney() {
		
		Racun racun = new Racun(2,2,"");
		Racun.racuni.add(racun);
		racun.setIznosNaRacunu(5);
		Assert.assertEquals(racun.getIznosNaRacunu(), 5 ,3);
		
	}
}
