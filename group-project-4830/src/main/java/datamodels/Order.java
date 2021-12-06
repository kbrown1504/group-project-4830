package datamodels;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;

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
	
	//Return html friendly format of data fields of this object
	//Servlet context is needed to pull books
	public String getHTML(ArrayList<BookListing> books)
	{
		String htmlStr = String.format(
				"<div style=\"padding:10px;margin-bottom:10px;"
				+ "border-radius:20px;box-shadow: 5px 5px 3px #aaaaaa;"
				+ "border: 1px solid #aaaaaa\">"
				+ "<h2>Order #%d</h2>",
				this.getID()
				);
		
		//Process Books from Order
		Iterator<BookListing> bookItr = books.iterator();
		while(bookItr.hasNext()) {
			htmlStr += "<hr>" + bookItr.next().getPlacedOrderHTML();
		}
		
		htmlStr += "</div>";
		return htmlStr;
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
