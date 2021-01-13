import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validacija {

	static Connection connection = (Connection) ConnectionManager.getInstance().getConnection();
	
	public static boolean returnTrueIfPasswordValid(String password) {
		
		if(password.length()>7) {
			
			return true;
			
		}
		
		return false;
	}
	
	public static boolean returnTrueIfUserDoesntExist(String username) throws SQLException {
		
		
		Statement st = connection.createStatement();
		
		String sql = "SELECT * FROM korisnici WHERE username='" + username + "'";

		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			
			if(rs.getString("username").equals(username)) {
			
			return false;
			
			}
		} 
		
		return true;
		
	}
	
}
