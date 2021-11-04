package datamodels;
import java.sql.*;

public class Order extends DataModel
{
	
	private int bookID, sellerID, buyerID;
	private String shippingAddress;
	public Order(int i, int b, int s, int bu, String sh)
	{
		id = i;
		bookID = b;
		sellerID = s;
		buyerID = bu;
		shippingAddress = sh;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Orders ( ID, BookID, SellerID, BuyerID, ShippingAddress) "
				+ "values (default, " + bookID + " ," + sellerID + " ," + buyerID + " ," + shippingAddress + ")");
		return temp;
	}
	
	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		String temp = "HTML or itemized return of Order object";
		return temp;
	}	
	
	//Get search statement to pull seller
	public PreparedStatement getSeller(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" SELECT * FROM Account WHERE ID = \"" + sellerID + "\";")	;
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
	
	public int getSellerID()
	{
		return sellerID;
	}
	
	public void setSellerID(int s)
	{
		sellerID = s;
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
