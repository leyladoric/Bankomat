import java.sql.SQLException;

public interface KorisniciDAOInterface  {

	void addKorisnik(Korisnik korisnik) throws SQLException;
	
	void removeKorisnik(Korisnik korisnik) throws SQLException;
	
	void changeName(String username, String name) throws SQLException;

	void changeLastname(String username, String lastname) throws SQLException;

	void changeUsername(String usernameNew, String username) throws SQLException;

	void changePassword(String username, String password) throws SQLException;
	
	Korisnik findKorisnik(String username)throws SQLException;
	
}
