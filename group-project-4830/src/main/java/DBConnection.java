import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
	

	public static void getDBConnection(ServletContext context) {
		servletContext = context;
		getDBConnection();
	}
	
	//TODO: Remove? I think we don't need this method
	public static boolean insert(Object obj, String tableName) {
		//TODO: Implement
		return false;
	}
	
	//TODO: may need ISBN as double or int
	public static ResultSet search(String category, String searchTerm) {
		ResultSet rs = null;
		
		/*
		 * Search query sorts results by relevance to search term
		 * SQL code reference: https://www.codexworld.com/how-to/sort-results-order-by-best-match-using-like-in-mysql/
		 */
		String sql = String.format("select * from BookListing where %s like '%s' "
				+ "and OrderID=0 "
				+ "order by "
				+ "case "
				+ "when %s like '%s' then 1 "
				+ "when %s like '%s' then 2 "
				+ "when %s like '%s' then 4 "
				+ "else 3 "
				+ "end",
				category, "%"+searchTerm+"%",
				category, searchTerm,
				category, searchTerm+"%",
				category, "%"+searchTerm+"%");
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
	public static ResultSet advancedSearch(String title, String author, String isbn, double priceLow, double priceHigh, int condition) {
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
	
	public static ResultSet getNewListings() {
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BookListing WHERE ORDERID = 0 ORDER BY ID DESC LIMIT 5";
		
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
	
	public static ResultSet getBook(int id) {
		ResultSet rs = null;
		String sql = "Select * from BookListing where ID = " + id + " LIMIT 1";
		
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
	
	public static ResultSet getSeller(int id) {
		ResultSet rs = null;
		String sql = "Select * from Account where ID = " + id + " LIMIT 1";
		
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
	public static ResultSet getSellerBooks(int sellerID) {
		ResultSet rs = null;
		String sql = "Select * from BookListing where OrderID = 0 and Seller = " + sellerID;
		
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
	public static ResultSet getSellerReviews(int sellerID) {
		ResultSet rs = null;
		String sql = "Select * from Reviews where SellerID = " + sellerID;
		
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
	
	public static ResultSet getCart(ArrayList<Integer> ids) {
		ResultSet rs = null;
		String sql = "Select * from BookListing where ID= ";
		
		Iterator<Integer> itr = ids.iterator();
		//Process first ID
		sql = sql + itr.next();
		while (itr.hasNext()) {
			sql += "OR ID=" + itr.next() + " ";
		}
		System.out.println(sql);
		
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