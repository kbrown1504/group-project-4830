

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class Book
 */
@WebServlet("/book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "View Book");
		
		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		}
		else {
			String i = request.getParameter("id");
			int id = Integer.parseInt(i);
			
			DBConnection.getDBConnection(this.getServletContext());
			ResultSet bookRs = DBConnection.getBook(id);
			
			try {
				BookListing book = DataParser.parseBookListing(bookRs).get(0);
				
				request.setAttribute("price", String.format("%.2f", book.getPrice()));
				request.setAttribute("sellerURL", "seller?id=" + book.getSellerID());
				request.setAttribute("title", book.getTitle());
				request.setAttribute("author", book.getAuthor());
				request.setAttribute("isbn", book.getISBN());
				request.setAttribute("info", book.getInfo());
				
				//Grab seller and set name
				ResultSet sellerRs = DBConnection.getSeller(book.getSellerID());
				Account seller = DataParser.parseAccount(sellerRs).get(0);
				request.setAttribute("seller", seller.getUsername());
				
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/book.jsp");
				view.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
