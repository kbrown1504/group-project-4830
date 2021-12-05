

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeaveReview
 */
@WebServlet("/leaveReview")
public class LeaveReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Leave a Review");
		
		//retrieve parameter that is passed in the URL. Called "forSeller" to track who the review is for.
		int forSeller = Integer.parseInt(request.getParameter("forSeller"));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/leaveReview.jsp");
		view.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO implement
		
		//Grab parameters for review
		
		//Attempt to save review and upload to database
		//ONLY one review per seller per user. We don't want spam...
		
		//On failure, post a failure message and doGet()
		
		//On success, redirect user to Seller page they navigated to this page from.
		
	}

}
