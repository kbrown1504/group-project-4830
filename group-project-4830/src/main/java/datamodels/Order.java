package datamodels;
import java.sql.*;

public class Order extends DataModel
{
	
	private int buyerID;
	private String shippingAddress;
	public Order(int i, int bu, String sh)
	{
		id = i;
		buyerID = bu;
		shippingAddress = sh;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement("INSERT INTO Orders (ID, BuyerID, ShippingAddress) "
				+ "values (default, " + buyerID + ", '" + shippingAddress + "')");
		return temp;
	}
	
	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		//TODO
		return "";
	}	

	//Get search statement to pull Book
	public PreparedStatement getBook(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" SELECT * FROM BookListing WHERE ID = \"" + id + "\";")	;
		return temp;		
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
