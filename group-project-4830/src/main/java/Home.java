

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;
import datamodels.DataParser;

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
		
		String htmlStr = "";
		
		DBConnection.getDBConnection(this.getServletContext());
		try {
			ArrayList<BookListing> books = DataParser.parseBookListing(DBConnection.getNewListings());
			Iterator<BookListing> it = books.iterator();
			while(it.hasNext()) {
				htmlStr += it.next().getCardHTML();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("books", htmlStr);
		
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
