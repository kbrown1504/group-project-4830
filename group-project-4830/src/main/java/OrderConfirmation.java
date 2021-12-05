

import java.io.IOException;
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
import datamodels.Order;

/**
 * Servlet implementation class OrderConfirmation
 */
@WebServlet("/OrderConfirmation")
public class OrderConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		} 
		else {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/orderConfirmation.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Create Order
		Order newOrder;
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		int buyerID = user.getID();
		String shippingAddr = request.getParameter("streetAddress") + " " + request.getParameter("city") + ", "
				+ request.getParameter("state") + " " + request.getParameter("zipCode");
		//OrderID set to 0, but will be initialized by DB when insert happens
		newOrder = new Order(0, buyerID, shippingAddr);
		
		//Insert Order to DB
		DBConnection.getDBConnection(this.getServletContext());
		boolean result = false;
		if(DBConnection.connection != null) {
			try {
				PreparedStatement insert = newOrder.create(DBConnection.connection);
				result = insert.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//If successful, grab order from DB
		//Change OrderID field of books in cart
		if (result) {
			ResultSet orderResult = DBConnection.getRecentOrder(buyerID);
			try {
				Order order = DataParser.parseOrder(orderResult).get(0);
				int orderID = order.getID();
				int rowsAffected = DBConnection.updateBooks(user.getCart(), orderID);
				if (rowsAffected == user.getCart().size()) {
					//Updates worked. Successful Order Placement. Reset Cart
					user.resetCarts();
				}
				else {
					//Something went wrong with the update statements
					System.out.println("DEBUG: Update of BookListings failed");
					throw new SQLException("ERROR: Order placement unsuccessful");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO: Redirect to Page showing Placed Order?
			response.sendRedirect("home");
		}
		//If insert fails, throw an error message.
		else {
			System.out.println("DEBUG: Insert of Order failed");
			request.setAttribute("message", "Order placement failed, please try again");
		}
	}

}
