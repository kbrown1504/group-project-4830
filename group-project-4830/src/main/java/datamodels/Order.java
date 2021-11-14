package datamodels;
import java.sql.*;

public class Order extends DataModel
{
	
	private int bookID, buyerID;
	private String shippingAddress;
	public Order(int i, int b, int bu, String sh)
	{
		id = i;
		bookID = b;
		buyerID = bu;
		shippingAddress = sh;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Orders ( ID, BookID, BuyerID, ShippingAddress) "
				+ "values (default, " + bookID + " ," + buyerID + " ," + shippingAddress + ")");
		return temp;
	}
	
	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		String temp = "HTML or itemized return of Order object";
		return temp;
	}	

	//Get search statement to pull Book
	public PreparedStatement getBook(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" SELECT * FROM BookListing WHERE ID = \"" + id + "\";")	;
		return temp;		
	}
	
	public int getbookID()
	{
		return bookID;
	}
	
	public void setBookID(int i)
	{
		bookID = i;
	}
	
	public int getBuyerID()
	{
		return buyerID;
	}
	
	public void setBuyerID(int b)
	{
		buyerID = b;
	}
	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String sh)
	{
		shippingAddress = sh;
	}
}
