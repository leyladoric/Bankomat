import java.beans.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class KorisniciDAOImplementation implements KorisniciDAOInterface {

	Connection connection = (Connection) ConnectionManager.getInstance().getConnection();
	Scanner input = new Scanner(System.in);
	
	@Override
	public void addKorisnik(Korisnik korisnik) throws SQLException {
		
		
		String query = "INSERT INTO korisnici(name, surname, username, password) VALUES (?, ?, ?, ?)";

		try (
						
			PreparedStatement statement = connection.prepareStatement(query); ){

					
			statement.setString(1, korisnik.getName());
			statement.setString(2, korisnik.getSurname());
			statement.setString(3, korisnik.getUsername());
			statement.setString(4, korisnik.getPassword());

			statement.executeUpdate();
		

			System.out.println("User added to the database.");
					
		} 
		
	}

	@Override
	public void removeKorisnik(Korisnik korisnik) {
		
	}

	
	@Override
	public Korisnik findKorisnik(String username) throws SQLException {
		
		Korisnik korisnik = null;

		String query = "SELECT * FROM korisnici WHERE username = ?";

		ResultSet rs = null;

		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, username);

			rs = statement.executeQuery();

			if (rs.next()) {
				
				korisnik = new Korisnik (rs.getString("name"), rs.getString("surname"),
						rs.getString("username"), rs.getString("password"));
				return korisnik;

				
			}
		}

		rs.close();
		
		return null;				
		
	}

	@Override
	public void changeName(String username, String name) throws SQLException {
		
		String query = "UPDATE korisnici SET name = ? WHERE username = ? ";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, name);
			statement.setString(2, username);

			statement.executeUpdate();
			
		}
		
	}

	@Override
	public void changeLastname(String username, String surname) throws SQLException {

		String query = "UPDATE korisnici SET surname = ? WHERE username = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, surname);
			statement.setString(2, username);

			statement.executeUpdate();
		}
		
	}

	@Override
	public void changeUsername(String usernameNew, String username) throws SQLException {
		
		String query = "UPDATE korisnici SET username = ? WHERE username = ?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, usernameNew);
			statement.setString(2, username);

			statement.executeUpdate();
		}
		
	}

	@Override
	public void changePassword(String username, String password) throws SQLException {
		
		String query = "UPDATE korisnici SET password = ? WHERE username = ?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, password);
			statement.setString(2, username);

			statement.executeUpdate();
		}
		
	}
}
