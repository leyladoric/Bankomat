import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

import org.junit.Assert;
import org.junit.Test;

import com.sun.tools.javac.Main;

public class BankomatTest {

	@Test
	public void shouldReturnTrueWhenInputIsValid() {
		
		Assert.assertTrue(Bankomat.shouldReturnTrueIfUnosIsValid(3));
		
	}

	
	@Test
	public void shouldReturnFalseWhenInputIsValid() {
		
		Assert.assertFalse(Bankomat.shouldReturnTrueIfUnosIsValid(-1));
		
	}
	
	@Test
	public void shouldReturnFalseWhenSourceAccountDoesntExist() {
		
		Racun targetAccount = new Racun(2,1,"c");
		Racun.racuni.add(targetAccount);
		Assert.assertFalse(Bankomat.provjeraTransfera(3, 2, 1));
	}
	
	@Test
	public void shouldReturnFalseWhenTargetAccountDoesntExist() {
		
		Racun sourceAccount = new Racun(2,1,"c");
		Racun.racuni.add(sourceAccount);
		Assert.assertFalse(Bankomat.provjeraTransfera(2, 3, 1));
	}
	
	
	@Test
	public void shouldReturnFalseBecauseThereIsNotEnoughMoneyOnSourceAccount() {
		
		Racun sourceRacun = new Racun(2,5,"");
		Racun.racuni.add(sourceRacun);
		
		Racun targetRacun = new Racun(3,2,"");
		Racun.racuni.add(targetRacun);
		
		Assert.assertFalse(Bankomat.provjeraTransfera(2, 3, 6));
		
	}
	
	@Test 
	public void shouldTransferMoneyWhenInvoked() {
		
		Racun sourceRacun = new Racun(8,9,"k");
		Racun.racuni.add(sourceRacun);
		Racun targetRacun = new Racun(89,6,"l");
		Racun.racuni.add(targetRacun);
		int iznosKojiSePrebacuje = 2;
		Bankomat.transferNovca(sourceRacun.getBrojRacuna(), targetRacun.getBrojRacuna(), iznosKojiSePrebacuje);
		System.out.println(sourceRacun.getIznosNaRacunu());
		System.out.println(targetRacun.getIznosNaRacunu());
		Assert.assertEquals(sourceRacun.getIznosNaRacunu(), 7, iznosKojiSePrebacuje);
		Assert.assertEquals(targetRacun.getIznosNaRacunu(), 8, iznosKojiSePrebacuje);
		
		
	}
	

	@Test
	public void shouldReturnTrueWhenCreatingNewAccountEverythingFine() throws EOFException, IOException {
		
		Assert.assertTrue("Ovaj racun se moze napraviti ", Bankomat.provjeraKreiranjaRacuna(7, 4, "v"));
		
	}
		
	@Test
	public void shouldReturnFalseWhenCreatingNewAccountInvalidAccountMoney() throws EOFException, IOException {
		
		Assert.assertFalse("Ovaj racun se ne moze napraviti ", Bankomat.provjeraKreiranjaRacuna(2, -1, "v"));
		
	}
	
	@Test
	public void shouldReturnFalseWhenCreatingNewAccountBecauseAccountAlreadyExists() throws EOFException, IOException {
		
		Racun racunKojiVecPostoji = new Racun(1,1,"v");
		Racun.racuni.add(racunKojiVecPostoji);
		Assert.assertFalse("Ovaj racun se ne moze napraviti ", Bankomat.provjeraKreiranjaRacuna(1, 1, "v"));
		
	}
	
	@Test
	public void shouldReturnFalseWhenCreatingNewAccountBecauseAccountNumberIsNegative() throws EOFException, IOException {
		
		Assert.assertFalse("Ovaj racun se ne moze napraviti ", Bankomat.provjeraKreiranjaRacuna(-2, 1, "v"));
		
	}
	
	@Test
	public void shouldReturnTrueIfTheAccountExistsSoItCanDisplayData() throws EOFException, IOException {
		
		Racun racunKojiVecPostoji = new Racun(5,7,"j");
		Racun.racuni.add(racunKojiVecPostoji);
		Assert.assertTrue(Bankomat.ispisi(5));
		
	}
	
	@Test
	public void shouldReturnFalseIfTheAccountDoesntExistSoItCantDisplayData() throws EOFException, IOException {
		
	
		Assert.assertFalse(Bankomat.ispisi(99));
		
	}
	
	@Test
	public void shouldReturnTrueIfTheAccountIsCreated() throws EOFException, IOException {
		
		Assert.assertTrue(Bankomat.napravljenRacun(10, 10, "leks"));
		
	}
	
	@Test
	public void shouldReturnFalseIfTheAccountIsNotCreated() throws EOFException, IOException {
		
		Assert.assertFalse(Bankomat.napravljenRacun(-8, 7, "leks"));
		
	}
	
	
	@Test
	public void shouldReturnFalseIfInputisWrong() throws EOFException, IOException {
		
		Assert.assertFalse(Bankomat.opcija(6));
		
	}
	
	@Test
	public void shouldReturnTrueIfTransferHappened() {
		
		Racun sourceRacun = new Racun(8,9,"k");
		Racun.racuni.add(sourceRacun);
		Racun targetRacun = new Racun(89,6,"l");
		Racun.racuni.add(targetRacun);
		
		Assert.assertTrue(Bankomat.prijeProvjereTransfera(8, 89, 1));
		
	}
	@Test
	public void shouldReturnFalseIfTransferDidntHappen() {
		
		Racun sourceRacun = new Racun(8,9,"k");
		Racun.racuni.add(sourceRacun);
		Racun targetRacun = new Racun(89,6,"l");
		Racun.racuni.add(targetRacun);
		
		Assert.assertFalse(Bankomat.prijeProvjereTransfera(8, 89, 10));
		
	}
}
