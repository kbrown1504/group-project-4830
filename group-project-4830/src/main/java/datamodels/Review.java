package datamodels;
import java.sql.*;

public class Review {

	private int id, sellerID, buyerID, rating;
	private String reviewText;
	public Review(int i, int s, int b, int r, String rev)
	{
		id = i;
		sellerID = s;
		buyerID = b;
		rating = r;
		reviewText = rev;
	}
	
	//Create mySQL statement from object
	public PreparedStatement create(Connection con) throws SQLException
	{
		PreparedStatement temp = con.prepareStatement(" INSERT INTO Reviews ( ID, SellerID, BuyerID, Rating, ReviewText) "
				+ "values (default, " + sellerID + " ," + buyerID + " ," + rating + " ," + reviewText + ")");
		return temp;
	}

	//Return some kind of object that is html friendly format of data fields of this.object
	public String getHTML()
	{
		String temp;
		return temp;
	}

	//Setter and getter methods for all values
	public int getID()
	{
		return id;
	}
	//No setter method as MySQL controls id
	
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
	
	public int getRating()
	{
		return rating;
	}
	
	public void setRating(int r)
	{
		rating = r;
	}
	
	public String getReviewText()
	{
		return reviewText;
	}
	
	public void setReviewText(String rev)
	{
		reviewText = rev;
	}
}
