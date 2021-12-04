

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Search Results");
		
		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("search.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("categories");
		String search = request.getParameter("search");
		
		request.setAttribute("category", category);
		request.setAttribute("search", search);
		
		//This switch selects the proper item in the drop-down menu
		switch(category) {
		case "TITLE":
			request.setAttribute("titleSelected", "selected");
			break;
		case "AUTHOR":
			request.setAttribute("authorSelected", "selected");
			break;
		case "ISBN":
			request.setAttribute("isbnSelected", "selected");
			break;
		default:
			break;
		}
		
		DBConnection.getDBConnection(this.getServletContext());
		
		try {
			ResultSet booksRs = DBConnection.search(category, search);
			ArrayList<BookListing> books;
			books = DataParser.parseBookListing(booksRs);
			Iterator<BookListing> booksItr = books.iterator();
			String booksHTML = "";
			while(booksItr.hasNext()) {
				booksHTML += booksItr.next().getCardHTML();
			}
			request.setAttribute("searchResults", booksHTML);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
