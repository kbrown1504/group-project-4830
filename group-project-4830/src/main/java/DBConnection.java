import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class DBConnection {
	
	static Connection connection = null;
	static ServletContext servletContext;
	
	static void getDBConnection() {
		UtilProp.loadProperty(servletContext);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public DBConnection(ServletContext context) {
		servletContext = context;
		getDBConnection();
	}
	
	public boolean insert(Object obj) {
		//TODO: Implement
		return false;
	}
	
	//TODO: may need ISBN as double or int
	public ResultSet search(String category, String searchTerm) {
		ResultSet rs = null;
		
		String sql = String.format("select * from BookListing where %s like '%s'", category, "%"+searchTerm+"%");
		if (connection != null) {
			try {
				PreparedStatement prepState = connection.prepareStatement(sql);
				try {
					rs = prepState.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rs;		
	}
	
	//TODO: may need ISBN as double or int
	public ResultSet advancedSearch(String title, String author, String isbn) {
		ResultSet rs = null;
		
		title = "%"+title+"%";
		author = "%"+author+"%";
		isbn = "%"+isbn+"%";
		
		String sql = String.format("select * from BookListing where Title like '%s' and Author like '%s' and ISBN like '%s'",
				title, author, isbn);
		if (connection != null) {
			try {
				PreparedStatement prepState = connection.prepareStatement(sql);
				try {
					rs = prepState.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rs;
	}
	
	static String getURL() {
		return UtilProp.getProp("url");
	}
	static String getUserName() {
		return UtilProp.getProp("user");
	}
	static String getPassword() {
		return UtilProp.getProp("password");
	}
	
}