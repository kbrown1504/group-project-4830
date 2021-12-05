

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
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
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
			DBConnection.getDBConnection(this.getServletContext());
			//Grab stored cart IDs from Account Object
			ArrayList<Integer> cartIds = ((Account)request.getSession().getAttribute("user")).getCartIDs();
			
			//Pull books in cart using IDs
			if (cartIds.size() > 0) {
				ResultSet rs = DBConnection.getCart(cartIds);
				ArrayList<BookListing> shoppingCart;
				try {
					//Decode result from DB
					shoppingCart = DataParser.parseBookListing(rs);
					//cache decoded cart to use later if needed
					user.setCart(shoppingCart);
					
					double itemCosts = 0;
					for (int i = 0; i < shoppingCart.size(); i++)
					{
						itemCosts += shoppingCart.get(i).getPrice();
					}
					double tax = itemCosts * 0.07;
					double shipping = itemCosts * 0.08;
					double finalCost = itemCosts + tax + shipping;
					
					String itemCostsStr = String.format("%.2f", itemCosts);
					String taxStr = String.format("%.2f", tax);
					String shippingStr = String.format("%.2f", shipping);
					String finalCostStr = String.format("%.2f", finalCost);
					
					request.setAttribute("itemCosts", itemCostsStr);
					request.setAttribute("tax", taxStr);
					request.setAttribute("shipping", shippingStr );
					request.setAttribute("finalCost", finalCostStr);
					
					String booksHtml = "";
					Iterator<BookListing> bookItr = shoppingCart.iterator();
					while (bookItr.hasNext()) {
						booksHtml += bookItr.next().getCardHTML();
					}
					
					request.setAttribute("books", booksHtml);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				request.setAttribute("hideCheckout", "hidden");
			}
			
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/cart.jsp");
			view.forward(request, response);
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
