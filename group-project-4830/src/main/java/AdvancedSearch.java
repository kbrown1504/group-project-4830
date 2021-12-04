

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
import javax.servlet.http.HttpSession;

import datamodels.Account;
import datamodels.BookListing;
import datamodels.DataParser;

/**
 * Servlet implementation class AdvancedSearch
 */
@WebServlet("/advancedSearch")
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
		//TODO: Move DB Call?
		
		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		} 
		else {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/advancedSearch.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
			DBConnection.getDBConnection(getServletContext());
			connection = DBConnection.connection;
			
			ArrayList<String> input = new ArrayList<String>();
			String selectSQL = "";
			if (!title.isEmpty())
			{
				title = '%' + title + '%';
				selectSQL = selectSQL.concat(" AND TITLE LIKE ?");
				input.add(title);
			}
			if (!author.isEmpty())
			{
				author = '%' + author + '%';
				selectSQL = selectSQL.concat(" AND AUTHOR LIKE ?");
				input.add(author);
			}
			if (!isbn.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND ISBN = ?");
				input.add(isbn);
			}
			if (!priceMin.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND PRICE >= ?");
				input.add(priceMin);
			}
			if (!priceMax.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND Price <= ?");
				input.add(priceMax);
			}
			if (!conditionMin.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND QUALITY >= ?");
				input.add(conditionMin);
			}
			if (!conditionMax.isEmpty())
			{
				selectSQL = selectSQL.concat(" AND QUALITY <= ?");
				input.add(conditionMax);
			}
			
			selectSQL = "SELECT * FROM BookListing where OrderID=0 ".concat(selectSQL);
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
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/advancedSearch.jsp");
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

}
