package datamodels;
import java.sql.*;

public abstract class DataModel {
	protected int id;
	
	public int getID()
	{
		return id;
	}
	
	public abstract PreparedStatement create(Connection con) throws SQLException;

}
