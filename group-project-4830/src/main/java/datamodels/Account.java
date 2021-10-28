package datamodels;
import java.sql.*;

public class Account 
{
	//Appropriate storage fields for MySQL fields
	private int id;
	private String username, email, password;
	public Account(int i, String us, String em, String pw)
	{
		id = i;
		username = us;
		email = em;
		password = pw;
	}
	
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Account ( ID, Username, Email, Password) values (default, "
				+ username + " ," + email + " ," + password + ")");
		return temp;
	}
	
	//Setter and getter methods for all values
	public int getID()
	{
		return id;
	}
	//No set id as it is a value controlled by MySQL
	
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

}
