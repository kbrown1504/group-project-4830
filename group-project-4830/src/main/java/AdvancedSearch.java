

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;
import datamodels.DataParser;

/**
 * Servlet implementation class AdvancedSearch
 */
@WebServlet("/AdvancedSearch")
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearch() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String priceMin = request.getParameter("priceMin");
		String priceMax = request.getParameter("priceMax");
		String conditionMin = request.getParameter("conditionMin");
		String conditionMax = request.getParameter("conditionMax");
		String seller = request.getParameter("seller");
		
		response.setContentType("text/html");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			DBConnection.DBConnection(getServletContext());
			connection = DBConnection.connection;
			
			ArrayList<String> input = new ArrayList<String>();
			String selectSQL = "";
			if (!title.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE TITLE = ?");
				input.add(title);
			}
			if (!author.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE AUTHOR = ?");
				input.add(author);
			}
			if (!isbn.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE ISBN = ?");
				input.add(isbn);
			}
			if (!priceMin.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE PRICE >= ?");
				input.add(priceMin);
			}
			if (!priceMax.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE Price <= ?");
				input.add(priceMax);
			}
			if (!conditionMin.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE CONDITION >= ?");
				input.add(conditionMin);
			}
			if (!conditionMax.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE CONDITION <= ?");
				input.add(conditionMax);
			}
			if (!seller.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND WHERE SELLER = ?");
				input.add(seller);
			}
			
			if(selectSQL.length() > 0)
			{
				selectSQL = selectSQL.substring(4);
			}
			selectSQL = "SELECT * FROM BookListing".concat(selectSQL);
			preparedStatement = connection.prepareStatement(selectSQL);
			for(int i = 0; i < input.size(); i++)
			{
				preparedStatement.setString(i + 1, input.get(i));
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<BookListing> bookListingSet = DataParser.parseBookListing(resultSet);
			String outHTML = "";
			for (int i = 0; i < bookListingSet.size(); i++)
			{
				outHTML = outHTML.concat(bookListingSet.get(i).getCardHTML());
			}
			
			request.setAttribute("books", outHTML);
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		}
		catch (SQLException se) 
	      {
	         se.printStackTrace();
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      } 
	      finally 
	      {
	         try 
	         {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } 
	         catch (SQLException se2) 
	         {
	         }
	         try 
	         {
	            if (connection != null)
	               connection.close();
	         } 
	         catch (SQLException se) 
	         {
	            se.printStackTrace();
	         }
	      }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
