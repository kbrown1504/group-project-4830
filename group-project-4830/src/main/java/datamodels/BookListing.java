package datamodels;
import java.sql.*;

public class BookListing extends DataModel
{
	private int sellerID,condition, orderID;
	private String title, author,info;
	private double price;
	private long isbn;
	public BookListing(int i, int oid, String titl, String auth, long ISB, double pric, int s, int cond, String inf)
	{
		id = i;
		orderID = oid;
		title = titl;
		author = auth;
		isbn = ISB;
		price = pric;
		sellerID = s;
		condition = cond;
		info = inf;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO BookListing ( ID, OrderID, Title, Author, ISBN, Price, Seller, Quality"
				+ ", AddInfo) VALUES (default, " + orderID + ", \'" + title + "\', \'" + author + "\', " + isbn + ", " + price + ", " + sellerID 
				+ ", " + condition + ", \'" + info + "\');");
		return temp;
	}
	
	//Return a html card with book info
	public String getCardHTML()
	{
		String htmlStr = String.format(
				"<div style=\"display:flex;position:relative;padding:10px;margin-bottom:10px;"
				+ "border-radius:20px;box-shadow: 5px 5px 3px #aaaaaa;"
				+ "border: 1px solid #aaaaaa\">"
				+ "<img src=%s height=\"%s\">"
				+ "<div style=\"display:inline-block;padding-left:10px;\">"
				+ "<a style=\"font-size:1.5rem;font-weight:bold;\" href=\"book?id=%d\">%s</a>"
				+ "<h2>$%.02f</h2>"
				+ "<h3>Author: %s</h3>"
				+ "<h3>ISBN: %d</h3>"
				+ "</div>"
				+ "</div>",
				"https://cdn-icons-png.flaticon.com/512/224/224641.png", "200", id, title, price, author, isbn
				);
		return htmlStr;
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	
	public void setOrderID(int i)
	{
		orderID = i;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String titl)
	{
		title = titl;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String auth)
	{
		author = auth;
	}
	
	public long getISBN()
	{
		return isbn;
	}
	
	public void setISBN(long isb)
	{
		isbn = isb;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double pric)
	{
		price = pric;
	}
	
	public int getSellerID()
	{
		return sellerID;
	}
	
	public void setSellerID(int s)
	{
		sellerID = s;
	}
	
	public int getCondition()
	{
		return condition;
	}
	public String getConditionStr() {
		switch (this.condition) {
			case 1:
				return "Poor";
			case 2:
				return "Average";
			case 3:
				return "Good";
			case 4:
				return "Very Good";
			case 5:
				return "Like New";	
			default:
				return "None Specified";
		}
	}
	
	public void setCondition(int cond)
	{
		condition = cond;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public void setInfo(String inf)
	{
		info = inf;
	}
	
	public String toString()
	{
		return id + " " + orderID + " " + title + " " + author + " " + isbn + " " + price + " " + sellerID + " " + condition + " " + info + "\n";
	}
}