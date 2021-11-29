

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;

/**
 * Servlet implementation class Seller
 */
@WebServlet("/seller")
public class Seller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Seller Profile");
		
		String i = request.getParameter("id");
		int id = Integer.parseInt(i);
		
		//fake seller data TODO: remove and replace with real data
		BookListing test1 = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131.0, 40.00, 0, 1, "test info");
		request.setAttribute("sellerBooks", test1.getCardHTML());
		request.setAttribute("sellerName", "John Doe");
		
		RequestDispatcher view = request.getRequestDispatcher("seller.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
