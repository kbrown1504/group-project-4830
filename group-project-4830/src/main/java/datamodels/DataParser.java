package datamodels;
import java.sql.*;
import java.util.ArrayList;

public class DataParser {
	
	//Hand in result set and a subclass DataModel to identify type
	public static <T extends DataModel> ArrayList<? extends DataModel> parse(ResultSet res, T master) throws SQLException
	{
		ArrayList<? extends DataModel> ret = new ArrayList<DataModel>();
		switch(master.getClass().toString()) 
		{
			case "Account":
				ArrayList<Account> tempA = new ArrayList<Account>();
				while (res.next())
				{
					Account t1 = new Account(res.getInt("ID"), res.getString("Username"), res.getString("Email"), res.getString("Password"));
					tempA.add(t1);
				}
				ret = tempA;
				break;
			case "BookListing": //BookListing code
				ArrayList<BookListing> tempB = new ArrayList<BookListing>();
				while (res.next())
				{
					BookListing t1 = new BookListing(res.getInt("ID"), res.getString("Title"), res.getString("Author"), 
							res.getDouble("ISBN"), res.getDouble("Price"), res.getInt("Seller"), res.getInt("Condition"),
							res.getString("AddInfo)"));
					tempB.add(t1);
				}
				ret = tempB;
				break;
			case "Order": //Order code
				ArrayList<Order> tempC = new ArrayList<Order>();
				while (res.next())
				{
					Order t1 = new Order(res.getInt("ID"), res.getInt("BookID"), res.getInt("SellerID"), res.getInt("BuyerID"),
							res.getString("ShippingAddress"));
					tempC.add(t1);
				}
				ret = tempC;
				break;
			case "Review": //Review code
				ArrayList<Review> tempD = new ArrayList<Review>();
				while (res.next())
				{
					Review t1 = new Review(res.getInt("ID"), res.getInt("SellerID"), res.getInt("BuyerID"), res.getInt("Rating"),
							res.getString("ReviewText"));
					tempD.add(t1);
				}
				ret = tempD;
				break;
			default: //Error case
				break;
		}
		return ret;
	}
	
	
}
