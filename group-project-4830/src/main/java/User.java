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
import datamodels.Order;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "My Account");

		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		} 
		else {
			DBConnection.getDBConnection(this.getServletContext());
			
			try {
				//get my orders
				ResultSet orderRs = DBConnection.getOrders(user.getID());
				ArrayList<Order> orders = DataParser.parseOrder(orderRs);
				String ordersHTML = "";
				Iterator<Order> orderItr = orders.iterator();
				while(orderItr.hasNext()) {
					Order order = orderItr.next();
					ArrayList<BookListing> booksInOrder = DataParser.parseBookListing(DBConnection.getOrderBooks(order.getID()));
					ordersHTML += order.getHTML(booksInOrder);
				}
				request.setAttribute("orders", ordersHTML);
				
				//get myListings
				ResultSet booksRs = DBConnection.getSellerBooks(user.getID());
				ArrayList<BookListing> sellerBooks;
				sellerBooks = DataParser.parseBookListing(booksRs);
				String booksHTML = "";
				Iterator<BookListing> bookItr = sellerBooks.iterator();
				while (bookItr.hasNext()) {
					booksHTML += bookItr.next().getMyListingHTML();
				}
				request.setAttribute("myListings", booksHTML);
				
				request.setAttribute("username", user.getUsername());				
				request.setAttribute("userEmail", user.getEmail());
				
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/user.jsp");
				view.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		response.sendRedirect("login");
	}

}
