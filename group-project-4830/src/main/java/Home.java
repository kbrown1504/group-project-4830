

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Home");
		
		BookListing test1 = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131.0, 40.00, 0, 1, "test info");
		BookListing test2 = new BookListing(0, -1, "Invitation to Cryptology", "Thomas Barr", 0130889768.0, 30.00, 0, 1, "test info");
		BookListing test3 = new BookListing(0, -1, "Attacking Network Protocols", "James Forshaw", 9781593277505.0, 40.00, 0, 1, "test info");
		
		request.setAttribute("books", test1.getCardHTML() + test2.getCardHTML() + test3.getCardHTML());
		
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
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
