package datamodels;
import java.sql.*;
import java.util.ArrayList;

public class DataParser {
	
	public static ArrayList<Account> parseAccount(ResultSet res) throws SQLException
	{
		ArrayList<Account> temp = new ArrayList<Account>();
		while (res.next())
		{
			Account t1 = new Account(res.getInt("ID"), res.getString("Username"), res.getString("Email"), res.getString("Password"));
			temp.add(t1);
		}
		return temp;
	}
	
	public static ArrayList<BookListing> parseBookListing(ResultSet res) throws SQLException
	{
		ArrayList<BookListing> temp = new ArrayList<BookListing>();
		while (res.next())
		{
			BookListing t1 = new BookListing(res.getInt("ID"), res.getInt("OrderID"), res.getString("Title"), res.getString("Author"), 
					res.getLong("ISBN"), res.getDouble("Price"), res.getInt("Seller"), res.getInt("Condition"),
					res.getString("AddInfo)"));
			temp.add(t1);
		}
		return temp;
	}
	
	public static ArrayList<Order> parseOrder(ResultSet res) throws SQLException
	{
		ArrayList<Order> temp = new ArrayList<Order>();
		while (res.next())
		{
			Order t1 = new Order(res.getInt("ID"), res.getInt("BuyerID"),
					res.getString("ShippingAddress"));
			temp.add(t1);
		}
		return temp;
	}
	
	public static ArrayList<Review> parseReview(ResultSet res) throws SQLException
	{
		ArrayList<Review> temp = new ArrayList<Review>();
		while (res.next())
		{
			Review t1 = new Review(res.getInt("ID"), res.getInt("SellerID"), res.getInt("BuyerID"), res.getInt("Rating"),
					res.getString("ReviewText"));
			temp.add(t1);
		}
		return temp;
	}

	
	
}
