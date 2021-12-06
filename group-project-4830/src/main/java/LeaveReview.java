

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.Account;
import datamodels.DataParser;
import datamodels.Review;

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
		request.setAttribute("sellerID", forSeller);
		
		DBConnection.getDBConnection(this.getServletContext());
		try {
			Account seller = (Account)DataParser.parseAccount(DBConnection.getSeller(forSeller)).get(0);
			
			request.setAttribute("sellerName", seller.getUsername());
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/leaveReview.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Grab parameters for review
		int sellerId = Integer.parseInt(request.getParameter("sellerID"));
		int userId = ((Account)request.getSession().getAttribute("user")).getID();
		int rating = Integer.parseInt(request.getParameter("rating"));
		String reviewText = request.getParameter("reviewText");
		
		Review toAdd = new Review(0, sellerId, userId, rating, reviewText);
		//Attempt to save review and upload to database
		//ONLY one review per seller per user. We don't want spam...
		DBConnection.getDBConnection(this.getServletContext());
		ResultSet reviewRs = DBConnection.getReview(sellerId, userId);
		//check to see if duplicate review exists
		try {
			if(reviewRs.next()) {
				//TODO remove
				System.out.println("DUPE");
				request.setAttribute("message", "You have already submitted a review for this seller.");
				doGet(request, response);
			} else {
				//TODO remove
				System.out.println("Good");
				PreparedStatement prepState = toAdd.create(DBConnection.connection);
				int rowsAffected = prepState.executeUpdate();
				if(rowsAffected > 0) {
					response.sendRedirect("seller?id=" + sellerId);
				}
				else {
					request.setAttribute("message", "Failed to post review.");
					doGet(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
