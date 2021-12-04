

import java.io.IOException;
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
			BookListing test1 = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131L, 40.00, 0, 1, "test info");
			BookListing test2 = new BookListing(0, -1, "Invitation to Cryptology", "Thomas Barr", 9780130889768L, 30.00, 0, 1, "test info");
			BookListing test3 = new BookListing(0, -1, "Attacking Network Protocols", "James Forshaw", 9781593277505L, 40.00, 0, 1, "test info");
			ArrayList<BookListing> shoppingCart = new ArrayList<BookListing>();
			shoppingCart.add(test1);
			shoppingCart.add(test2);
			shoppingCart.add(test3);
			
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
			request.setAttribute("books", test1.getCardHTML() + test2.getCardHTML() + test3.getCardHTML());
			
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
