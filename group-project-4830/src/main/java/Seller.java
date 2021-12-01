

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

import datamodels.Account;
import datamodels.BookListing;
import datamodels.DataParser;
import datamodels.Review;

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
		
		DBConnection.getDBConnection(this.getServletContext());
		//Grab seller and set name
		try {
			//get Seller
			ResultSet sellerRs = DBConnection.getSeller(id);
			Account seller;
			seller = DataParser.parseAccount(sellerRs).get(0);
			request.setAttribute("sellerName", seller.getUsername());
			
			
			//get Seller Reviews
			ResultSet reviewsRs = DBConnection.getSellerReviews(id);
			ArrayList<Review> reviews = DataParser.parseReview(reviewsRs);
			String reviewsHTML = "";
			Iterator<Review> reviewItr = reviews.iterator();
			while (reviewItr.hasNext()) {
				//TODO: Add code for getting Review HTML
				reviewItr.next();
			}
			request.setAttribute("reviews", reviewsHTML);
			
			//get Seller Books
			ResultSet booksRs = DBConnection.getSellerBooks(id);
			ArrayList<BookListing> sellerBooks = DataParser.parseBookListing(booksRs);
			String booksHTML = "";
			Iterator<BookListing> bookItr = sellerBooks.iterator();
			while (bookItr.hasNext()) {
				booksHTML += bookItr.next().getCardHTML();
			}
			request.setAttribute("sellerBooks", booksHTML);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
