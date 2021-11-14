package datamodels;
import java.sql.*;

public class Account extends DataModel
{
	//Appropriate storage fields for MySQL fields
	private String username, email, password;
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
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Account ( ID, Username, Email, Password) values (default, "
				+ username + " ," + email + " ," + password + ")");
		return temp;
	}
	
	//No getHTML() for Account because of login security
	
	//Special login method
	public String login(String p)
	{
		if (p.contentEquals(password))
			return "Accepted html/statement push";
		else
			return "Invalid password html/push";
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

}
