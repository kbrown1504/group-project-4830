

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Search Results");
		
		RequestDispatcher view = request.getRequestDispatcher("search.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("categories");
		String search = request.getParameter("search");
		
		request.setAttribute("category", category);
		request.setAttribute("search", search);
		
		//This switch selects the proper item in the drop-down menu
		switch(category) {
		case "TITLE":
			request.setAttribute("titleSelected", "selected");
			break;
		case "AUTHOR":
			request.setAttribute("authorSelected", "selected");
			break;
		case "ISBN":
			request.setAttribute("isbnSelected", "selected");
			break;
		default:
			break;
		}
		
		BookListing test = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131.0, 40.00, 0, 1, "test info");
		
		request.setAttribute("searchResults", test.getCardHTML());
		
		doGet(request, response);
	}

}
