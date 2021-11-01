package datamodels;
import java.sql.*;

public class BookListing 
{
	private int id,sellerID,condition;
	private String title, author,info;
	private double isbn,price;
	public BookListing(int i, String titl, String auth, double ISB, double pric, int s, int cond, String inf)
	{
		id = i;
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
		PreparedStatement temp = con.prepareStatement(" INSERT INTO BookListing ( ID, Title, Author, ISBN, Price, Seller, Condition"
				+ ", AddInfo) values (default, " + title + " ," + author + " ," + isbn + " ," + price + " ," + sellerID 
				+ " ," + condition + " ," + info + ")");
		return temp;
	}
	
	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		String temp = "HTML or itemized return of Account object";
		return temp;
	}	
	
	//Setter and getter methods for all values
	public int getID()
	{
		return id;
	}
	//No setter as MySQL controlled value
	
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
	
	public double getISBN()
	{
		return isbn;
	}
	
	public void setISBN(double isb)
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
}