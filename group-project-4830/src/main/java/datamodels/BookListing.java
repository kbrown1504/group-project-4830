package datamodels;
import java.sql.*;

public class BookListing extends DataModel
{
	private int sellerID,condition;
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
				+ " ," + condition + " ," + info + ");");
		return temp;
	}
	
	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		String htmlStr = String.format(
				"<div style=\"display:flex;position:relative;padding:10px;"
				+ "border-radius:20px;box-shadow: 5px 5px 3px #aaaaaa;"
				+ "border: 1px solid #aaaaaa\">"
				+ "<img src=%s height=\"%s\">"
				+ "<div style=\"display:inline-block;padding-left:10px;\">"
				+ "<h2>%s</h2>"
				+ "<h2>$%.02f</h2>"
				+ "<h3>Author: %s</h3>"
				+ "<h3>ISBN: %.0f</h3>"
				+ "</div>"
				+ "</div>",
				"https://cdn-icons-png.flaticon.com/512/224/224641.png", "200", title, price, author, isbn
				);
		return htmlStr;
	}
	
	//Get search statement to pull seller
	public PreparedStatement getSeller(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" SELECT * FROM Account WHERE ID = \"" + sellerID + "\";")	;
		return temp;
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