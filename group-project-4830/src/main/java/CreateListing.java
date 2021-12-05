

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodels.Account;
import datamodels.BookListing;

/**
 * Servlet implementation class CreateListing
 */
@WebServlet("/createListing")
public class CreateListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateListing() 
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
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/createListing.jsp");
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
		String isbnString = request.getParameter("isbn");		
		String priceString = request.getParameter("price");		
		String conditionString = request.getParameter("condition");
		String addinfo = request.getParameter("addinfo");
		
		response.setContentType("text/html");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		int sellerID = user.getID();
		
		if (title.isEmpty() || author.isEmpty() || isbnString.isEmpty() || priceString.isEmpty() || conditionString.isEmpty())
		{
			request.setAttribute("message", "Error: You must enter valid values in each of the text boxes.");
		}
		else
		{
			long isbn = Long.valueOf(isbnString);
			double price = Double.valueOf(priceString);
			int condition = Integer.valueOf(conditionString);
			
			//Initializing id to -1 as a default value for id is used when executing the sql
			BookListing newListing = new BookListing(-1, 0, title, author, isbn, price, sellerID, condition, addinfo);		
			try
			{
				DBConnection.getDBConnection(getServletContext());
				connection = DBConnection.connection;
				preparedStatement = newListing.create(connection);
				System.out.println(preparedStatement.toString());
				preparedStatement.execute();
				connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("message", "Success! Your Book Listing is now Posted!");
		}
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/createListing.jsp");
		view.forward(request, response);
		
		
	}

}
