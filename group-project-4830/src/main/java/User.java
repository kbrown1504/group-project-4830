

import java.io.IOException;

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
		
		String i = request.getParameter("id");
		int id = Integer.parseInt(i);
		
		RequestDispatcher view = request.getRequestDispatcher("user.jsp");
		view.forward(request, response);
		
		//Check if a user is logged in
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		if (user == null) {
			//If they aren't, redirect to login
			response.sendRedirect("login");
		} 
		else {
			i = request.getParameter("id");
			id = Integer.parseInt(i);
			
			//Placeholder Info until db connects listings
			BookListing test11 = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131L, 40.00, 0, 1, "test info");
			request.setAttribute("userBooks", test11.getCardHTML());
			request.setAttribute("userName", "John Doe");
			
			view = request.getRequestDispatcher("WEB-INF/user.jsp");
			view.forward(request, response);
		}
		int placeholder = id;
		System.out.println(placeholder);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
