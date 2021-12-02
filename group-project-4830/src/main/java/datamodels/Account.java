package datamodels;
import java.sql.*;
import java.util.ArrayList;

public class Account extends DataModel
{
	//Appropriate storage fields for MySQL fields
	private String username, email, password;
	
	ArrayList<Integer> cartBookIDs = new ArrayList<Integer>();
	public Account(int i, String us, String em, String pw)
	{
		id = i;
		username = us;
		email = em;
		password = pw;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Account (ID, Username, Email, Password) VALUES (default, ?, ?, ?)");
		temp.setString(1, username);
		temp.setString(2, email);
		temp.setString(3, password);
		return temp;
	}
	
	//No getHTML() for Account because of login security
	
	
	public void addBookToCart(int idToAdd) {
		//TODO: implement
	}
	
	public void removeBookFromCart(int idToRemove) {
		//TODO: implement
	}
	
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String user)
	{
		username = user;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String em)
	{
		email = em;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassoword(String pw)
	{
		password = pw;
	}

	public String toString()
	{
		return id + " " + username + " " + email + " " + password + "\n";
	}
}
